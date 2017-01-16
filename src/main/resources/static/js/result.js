/**
 * Created by Simone on 15.01.2017.
 */

$('#textToSpeech').click(function () {
    var text = $('#resultText').text();
    responsiveVoice.speak(text);
});