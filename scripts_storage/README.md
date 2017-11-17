##Протокол описания сценариев

Каждый сценарий располагается в отдельной директории внутри scripts_storage 
c префиксом "script". 

Путь к scripts_storage указазывается в application.properties

Каждый сценарий содержит:

1. Изображение сожержащее искомый элемент с маской: "src_name.png"

2. Изображение сожержащее только искомый элемент с маской: "dst_name.png"

3. actions.properties файл в котором содержится:
  
  3.1 Путь к пакету сценария в случае успеха.
  3.2 Путь к пакету сценария в случае неудачи.    
  
  
  
Пример возможной структуры:
 
scripts_storage/script_login_into_yandex_mail/src_login_page.png
scripts_storage/script_login_into_yandex_mail/dst_login_area.png
scripts_storage/script_login_into_yandex_mail/actions.properties


Пример возможного содержания actions.properties :

success=../script_some_success

error=../script_some_error