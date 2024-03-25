select count(*) as FISH_COUNT
from FISH_INFO as info
left join FISH_NAME_INFO as name
on info.FISH_TYPE = name.FISH_TYPE
where name.FISH_NAME = "BASS" or 
name.FISH_NAME = "SNAPPER"