﻿(function () {
    angular.module('AvgGradeApp').constant('events', {
        message: {

            // Send file to server for processing
            _SEND_TO_PROCESS_: '_SEND_TO_PROCESS_',
            _SEND_TO_PROCESS_COMPLETE_: '_SEND_TO_PROCESS_COMPLETE_',
            _SEND_TO_PROCESS_FAILED_: '_SEND_TO_PROCESS_FAILED_',

            _FILE_UPLOADED_: '_FILE_UPLOADED_' // File has been uploaded to server, now begins extraction part in server
        }
    });
})();
