/**
 * Created by Simone on 15.01.2017.
 */

$('#textToSpeech').click(function () {
    console.log("Hello452354");
    var text = $('#resultText').text();
    responsiveVoice.speak(text);
});