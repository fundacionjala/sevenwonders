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

var bowerFiles = require('main-bower-files'),
    angularFilesort = require('gulp-angular-filesort'),
    inject = require('gulp-inject');


//var cssFiles  = lazypipe()
//            .pipe(
//            inject(
//                gulp.src('./src/main/javascript/app/**/*.less')
//              .pipe(less())
//            )
//            );
//
//
//var angularFiles  = lazypipe()
//      .pipe(inject(
//            gulp.src('./src/main/javascript/app/**/*.js')
//              .pipe(angularFilesort())
//            ));


var config = {
    paths: {
        // configurable paths
        app: 'angular-multimodule-server/src/main/webapp',
        test: 'src/test/javascript',
        dist: 'angular-multimodule-server/build/web'
    },
    ports: {
        proxy: 9000,
        app: 8080
    },
    jshint: {
        jshintrc: '.jshintrc',
        reporter: 'jshint-stylish'
    }
};

var cssFiles = gulp.src('./src/main/javascript/app/**/*.less')
  .pipe(less())
  .pipe(gulp.dest('./build'));


gulp.task('build', function (next) {
    gulp.src('./src/main/javascript/app/index.html')
      .pipe(inject(gulp.src(bowerFiles(), {read: false}), {name: 'bower'}))
      .pipe(inject(cssFiles, {name: 'css'}))
      .pipe(inject(gulp.src('./src/main/javascript/app/**/*.js').pipe(angularFilesort())))

//      .pipe(inject(es.merge(
//        cssFiles,
//        gulp.src('./src/main/javascript/app/**/*.js', {read: true})
//        .pipe(angularFilesort())

      .pipe(gulp.dest('./build'));
});


