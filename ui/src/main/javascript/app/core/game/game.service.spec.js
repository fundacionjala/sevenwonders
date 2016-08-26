'use strict';

describe('Phone', function() {
    var $httpBackend;
    var Game;
    var phonesData = [
        { name: 'Phone X' },
        { name: 'Phone Y' },
        { name: 'Phone Z' }
    ];

    // Add a custom equality tester before each test
    beforeEach(function() {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `Phone` service before each test
    beforeEach(module('sevenWonders.core.game'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function(_$httpBackend_, _Game_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.expectGET('games').respond(phonesData);

        Game = _Game_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should fetch the phones data from `/phones/phones.json`', function() {
        var phones = Game.query();

        expect(phones).toEqual([]);

        $httpBackend.flush();
        expect(phones).toEqual(phonesData);
    });

});