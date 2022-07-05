# Screenshots
<div style="display:flex;">
<img src="https://github.com/shawon5ice/Retrofit_EX/blob/master/screenshots/job_list.jpg" alt="alt text" width="200" height="400">
<img src="https://github.com/shawon5ice/Retrofit_EX/blob/master/screenshots/job_details.jpg" alt="alt text" width="200" height="400">
</div>
<br>
<hr>
This project is structured in MVVM pattern.
While calling API from retrofit service i encountered new issue that because of cleartext traffic. To solve this issue i used network_security.xml file which is called from Manifest file(though later i found there is an option to enable cleartext security in AndroidManifest application section). This issue is caused because the API is not secured thorough HTTPS protocol. 

### API : http://corporate3.bdjobs.com/interviewtest/interviewJson.json


