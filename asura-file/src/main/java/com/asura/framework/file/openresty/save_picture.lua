package.path = '/usr/local/nginx/lualib/resty/?.lua;' 
local upload = require "upload"
local osfilepath = "/dev/shm/"


local picture_format = {"jpg","png","gif","jpeg"}


local function get_args_data(key)
   local args_get  = ngx.req.get_uri_args()
   local args_post = ngx.req.get_post_args()
   if args_get[key] then
         return args_get[key]
   elseif args_post[key] then
         return args_post[key]
   end
end

local body_data = ngx.req.get_body_data()

local from = get_args_data("from")
local name = get_args_data("name")
local format = get_args_data("format")

ngx.say(get_args_data("zhaoyun"))
if from and name then
     if string.find(name,"/") or string.find(from,"/") then
        ngx.say("参数非法")
        return
     end
end


local picture_url = "http://192.168.0.17:8081"
--local picture_url = "http://picture.lvye.com:8081"
local picture_source = {"leader","myevent","order"}


local source = from 

-----------------------------------------------------------------------------
-----------------------------------------------------------------------------

local function check_picture_source(source)
    for k,v in pairs(picture_source) do
       if v == source then
           return true
       end
    end
    return nil 
end

local function check_file_type(picture_name)
    local picture_name_length = #picture_name
    for key,value in pairs(picture_format)
    do
        local  s,e = string.find(picture_name,"."..string.lower(value))
        if e == picture_name_length then
            return true
        end
    end
    return nil
end

if not check_picture_source(source) then
    ngx.say("picture source abnomal")
    return
end

local function get_picture_path()
   local time = os.date("%Y/%m/%d")
   url_path = "/"..source.."/"..time.."/"
   local dir = osfilepath..source.."/"..time.."/"
   os.execute("mkdir -p "..dir)
   return dir,picture_url..url_path
end

-----------------------------------------------------------------------------
-----------------------------------------------------------------------------

local function post_base64_data_to_picture()
     if not from  then 
          ngx.say("需要的参数: (get和post都可以,from=from)  post的数据(参数:data=base64后的图片数据)")
          return
     end

     if not format then
        format = ".png" 
     end

     local picture_name = os.time()..math.random(100,999).."."..format
     local args = ngx.req.get_post_args()
     if not args.data and not body_data then
       ngx.say("data参数没有或没有body数据")
       return 
     end
     picture_data = ""
     --if data then
     --     local data = string.gsub(args.data,' ','+')
     --     local picture_data = ngx.decode_base64(data)
     --end
     if not picture_data  and not body_data then
            ngx.say("base64 解析错误或没有body数据")
            return
     elseif body_data then
         picture_data = body_data
     end
     local filepath,url_path = get_picture_path()
     local file_name = filepath .. picture_name
     local file = io.open(file_name,"w+")
     if not file then
         ngx.say("failed to open file")
         return
     end
     file:write(picture_data)
     file:close()
     return  url_path..picture_name
end

result = ""
local chunk_size = 4096
local form = upload:new(chunk_size)
if not form then
    result = post_base64_data_to_picture()
    if not result then
         ngx.say('usage: curl http://192.168.0.17:8888/lua?from=order -d "data=base64_data"')
    else
         ngx.say(result)
    end
    return 
end

local file
local filelen=0
form:set_timeout(100) -- 1 sec
local picture_name




local function get_picture_name(res)
    local picture_name = ngx.re.match(res,'(.+)filename="(.+)"(.*)')
    if picture_name then 
        return picture_name[2]
    end
end

function debugs(str)
   filepath = "/tmp/upload.txt"
   files = io.open(filepath,"w+")
   if type(str) == "table" then
      for key,value in pairs(str) 
      do
          files:write(key.."\n")
          files:write(value)
      end
   else
     files:write(str)
   end
   files:close()
end

local function post_form_data_to_picture()
     local i=0
     while true do
         local typ, res, err = form:read()
         if not typ then
          ngx.say("failed to read: ", err)
          return
         end

         if typ == "header" then
          if res[1] ~= "Content-Type" then
              debugs(res[1])
              debugs(res[2])
              picture_name = get_picture_name(res[2])
              if picture_name then

               if not check_file_type(picture_name) then
                     ngx.say("Abnormal file extension")
                     return
               end
               i=i+1
               
               filepath,url_path = get_picture_path()
               file_name = filepath .. picture_name
               file = io.open(file_name,"w+")
               if not file then
                   ngx.say("failed to open file ")
                   return
               end
              end
          end

         elseif typ == "body" then
          if file then
              filelen= filelen + tonumber(string.len(res))
              file:write(res)
          end

         elseif typ == "part_end" then
          if file then
              file:close()
              ngx.say(url_path..picture_name)
          end
         elseif typ == "eof" then
          break
         end
     end
        if i==0 then
               ngx.say("please upload at least one file!")
               return
        end
end
