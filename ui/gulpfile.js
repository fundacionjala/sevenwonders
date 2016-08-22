'use strict';

var gulp = require('gulp'),
    bower = require('gulp-bower'),
    gutil = require('gulp-util'),
    debug = require('gulp-debug'),
    gulpif = require('gulp-if'),
    ngmin = require('gulp-ngmin'),
    newer = require('gulp-newer'),
    ngHtml2js = require('gulp-ng-html2js'),
    minifyhtml = require('gulp-htmlmin'),
    jshint = require('gulp-jshint'),
    less = require('gulp-less'),
    uglify = require('gulp-uglify'),
    rename = require('gulp-rename'),
    replace = require('gulp-replace'),
    header = require('gulp-header'),
    footer = require('gulp-footer'),
    clean = require('gulp-clean'),
    concat = require('gulp-concat'),
    usemin = require('gulp-usemin'),
    less = require('gulp-less'),
    rev = require('gulp-rev'),
    livereload = require('gulp-livereload'),
    cssmin = require('gulp-cssmin');

var lazypipe = require('lazypipe');
var path = require('path');
var fs = require('fs');
var es = require('event-stream');
var connect = require('connect');
var connect_livereload = require('connect-livereload');
var proxy_middleware = require('proxy-middleware');
var bs = require('browser-sync').create();
var series = require('stream-series');

var bowerFiles = require('main-bower-files'),
    angularFilesort = require('gulp-angular-filesort'),
    inject = require('gulp-inject'),
    watch = require('gulp-watch');


var config = {
    paths: {
        // configurable paths
        app: 'angular-multimodule-server/src/main/webapp',
        test: 'src/test/javascript',
        dist: 'angular-multimodule-server/build/web'
    },
    jshint: {
        jshintrc: '.jshintrc',
        reporter: 'jshint-stylish'
    }
};

gulp.task('bower', function() {
    return bower();
});

gulp.task('generateCSS', function() {
    return gulp.src('./src/main/javascript/app/**/*.less')
        .pipe(less())
        .pipe(gulp.dest('./src/main/javascript/app'));
});

gulp.task('lint', function() {
    return gulp.src(['src/main/javascript/app/**/*.js', '!src/main/javascript/app/{bower_components,bower_components/**}'])
        .pipe(jshint(config.jshint.jshintrc))
        .pipe(jshint.reporter(config.jshint.reporter))
        .pipe(jshint.reporter('fail'));
});

var bower = gulp.src(bowerFiles(), { read: false });
var css = gulp.src(['src/main/javascript/app/**/*.css', '!src/main/javascript/app/{bower_components,bower_components/**}'], { read: false });
var angularjs = gulp.src(['src/main/javascript/app/**/*.js',
    '!src/main/javascript/app/{bower_components,bower_components/**}'
]).pipe(angularFilesort());


gulp.task('buildIndex', function() {
    console.log('start inject');
    return gulp.src('src/main/javascript/app/index_base.html')
        .pipe(debug())
        .pipe(inject(series(css, bower, angularjs), { ignorePath: 'src/main/javascript/app' }))
        .pipe(rename('index.html'))
        .pipe(gulp.dest('src/main/javascript/app'));
});

gulp.task('browser-sync', function() {
    bs.init({
        server: {
            baseDir: "./src/main/javascript/app"
        }
    });
});

gulp.task('watch', ['buildIndex', 'generateCSS', 'browser-sync'], function() {
    gulp.watch("./src/main/javascript/app/**/*.less", ['less']);
    gulp.watch("./src/main/javascript/app/**/*.html").on('change', bs.reload);
    gulp.watch("./src/main/javascript/app/**/*.js").on('change', bs.reload);
});