/**
 * Created by Simone on 15.01.2017.
 */

$(document).ready(function () {
    $('#submitFile').prop('disabled', true);
    $('#loadingContainer').hide();
});

$('#file').bind('change', function () {
   console.log("SIZE: "+this.files[0].size);

   if(this.files != "undefined") {
       //file size must be < 1 Mb
       if(this.files[0].size > 1048576) {
          $('#fileTooBig').text("Selected file is too big. Maximum Image Size: 1 Mb");
          $('#submitFile').prop('disabled', true);
       }
       if(this.files[0].size > 0 && this.files[0].size < 1048576) {
          $('#fileTooBig').text("");
          $('#submitFile').prop('disabled', false);
       }
   }
});

$('#submitFile').click(function () {
    $('#loadingContainer').show();
});