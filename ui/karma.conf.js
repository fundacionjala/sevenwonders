module.exports = function(config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: 'src/main/javascript/app',
        frameworks: ['jasmine'],
        files: [
            'lib/lodash/dist/lodash.js',
            'lib/angular/angular.js',
            'lib/angular-resource/angular-resource.js',
            'lib/restangular/dist/restangular.js'
        ],
        // list of files / patterns to exclude
        exclude: [],
        plugins: [
            'karma-jasmine',
            'karma-coverage',
            'karma-chrome-launcher',
            'karma-phantomjs-launcher'
        ],
        // test results reporter to use
        // possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
        reporters: ['progress', 'coverage'],

        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['PhantomJS'],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false,

        // Coverage reporter generates the coverage
        coverageReporter: {
            reporters: [
                { type: 'lcov', dir: 'build/coverage/' },
                { type: 'text-summary', dir: 'build/coverage/' }
            ]
        }

    });
};