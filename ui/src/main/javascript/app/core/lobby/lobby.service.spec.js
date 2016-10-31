'use strict';

describe('Tests Lobby service', function () {
    var lobby;
    var Lobby;
    var $websocket;
    var $exceptionHandler;

    beforeEach(function () {
        module(function ($provide) {
            $provide.service('$websocket', function (data) {
                var url = data;
            });
        });
        module('sevenWonders.core.lobby');
    });
    beforeEach(module(function ($exceptionHandlerProvider) {
        $exceptionHandlerProvider.mode('log');
    }));
    beforeEach(inject(function (_$websocket_, _Lobby_, _$exceptionHandler_) {
        $websocket = _$websocket_;
        Lobby = _Lobby_;
        $exceptionHandler = _$exceptionHandler_;
    }));
    describe('.connectWs(lobby)', function () {
        it('should be defined ', function () {
            expect(Lobby).toBeDefined();
        });
        it('should connect to websocket to be defined', function () {
            expect(Lobby.connectWs).toBeDefined();
        });
        it('should return exception when trying to set a undefinded lobby', function () {
            expect($exceptionHandler.errors).toEqual([]);
            try {
                Lobby.connectWs(undefined);
            } catch (e) {
                $exceptionHandler(e);
            }
            expect($exceptionHandler.errors).toEqual(['Lobby is not defined']);
        });
    });
});