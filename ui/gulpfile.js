'use strict';

var gulp = require('gulp'),
    gulp_bower = require('gulp-bower'),
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
    clean = require('gulp-clean'),
    concat = require('gulp-concat'),
    usemin = require('gulp-usemin'),
    less = require('gulp-less'),
    rev = require('gulp-rev'),
    cssmin = require('gulp-cssmin');

var lazypipe = require('lazypipe');
var path = require('path');
var fs = require('fs');
var es = require('event-stream');
var bs = require('browser-sync').create();
var series = require('stream-series');

var bowerFiles = require('main-bower-files'),
    angularFilesort = require('gulp-angular-filesort'),
    inject = require('gulp-inject'),
    watch = require('gulp-watch');


var config = {
    paths: {},
    jshint: {
        jshintrc: '.jshintrc',
        reporter: 'jshint-stylish'
    }
};

gulp.task('bower', function() {
    return gulp_bower({ directory: './src/main/javascript/app/lib' })
});

gulp.task('generateCSS', function() {
    return gulp.src('./src/main/javascript/app/**/*.less')
        .pipe(less())
        .pipe(gulp.dest('./src/main/javascript/app'));
});

gulp.task('lint', function() {
    return gulp.src(['src/main/javascript/app/**/*.js', '!src/main/javascript/app/{lib,lib/**}'])
        .pipe(jshint(config.jshint.jshintrc))
        .pipe(jshint.reporter(config.jshint.reporter))
        .pipe(jshint.reporter('fail'));
});

gulp.task('buildIndex', function() {
    var bower = gulp.src(bowerFiles(), { read: false });
    var global = gulp.src(['src/main/javascript/app/style/*.css'], { read: false });
    var css = gulp.src(['src/main/javascript/app/**/*.css', '!src/main/javascript/app/{lib,lib/**}', '!src/main/javascript/app/{style,style/**}'], { read: false });
    var angularjs = gulp.src([
        '!src/main/javascript/app/{lib,lib/**}', '!src/main/javascript/app/**/*.spec.js',
        'src/main/javascript/app/**/*.js',
    ]).pipe(angularFilesort());
    return gulp.src('src/main/javascript/app/index_base.html')
        .pipe(debug())
        .pipe(inject(global, { ignorePath: 'src/main/javascript/app', name: 'global' }))
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
    gulp.watch("./src/main/javascript/app/**/*.less", ['generateCSS']);
    gulp.watch("./src/main/javascript/app/**/*.html").on('change', bs.reload);
    gulp.watch("./src/main/javascript/app/**/*.js").on('change', bs.reload);
});