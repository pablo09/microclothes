var gulp = require('gulp');
var concat = require('gulp-concat');
var eslint = require('gulp-eslint');
var inject = require('gulp-inject');
var gp = require('gulp-protractor');
var path = 'bower_components/';

var express = require('express');
var args = require('yargs').argv;
var http = require('http');
var isCI = args.type === 'ci';
var server = http.createServer(express().use(express.static(__dirname + '/build/')));
var compass = require('gulp-compass');
var watch = require('gulp-watch');

var connect = require('gulp-connect');

var htmlSrc = 'src/main/resources/static/**/*.html';
var jsSrc = 'src/main/resources/static/**/*.js';

var allSrc = htmlSrc.concat(jsSrc);

gulp.task('scripts', function () {
    return gulp.src([
        path + 'angular/angular.js',
        path + 'angular-ui-router/release/angular-ui-router.min.js',
        path + 'angular-translate/angular-translate.min.js',
        path + 'angular-resource/angular-resource.min.js',
        path + 'angular-sanitize/angular-sanitize.min.js',
        path + 'angular-bootstrap/ui-bootstrap.min.js',
        path + 'angular-bootstrap/ui-bootstrap-tpls.min.js',
        path + 'angular-bootstrap-multiselect/angular-bootstrap-multiselect.js',
        path + 'angular-animate/angular-animate.min.js',
        path + 'angular-toastr/dist/angular-toastr.tpls.min.js',
        path + 'lodash/dist/lodash.min.js',
        path + 'angular-block-ui/dist/angular-block-ui.min.js',
		path + 'angular-cookies/angular-cookies.min.js'
    ])
        .pipe(concat('scripts.js'))
        .pipe(gulp.dest('./src/main/resources/static/'));
});

gulp.task('lint', function () {
    return gulp.src(['./src/main/resources/static/**/*.js', '!./src/main/resources/static/scripts.js'])
        .pipe(eslint({
            extends: 'eslint:recommended',
            rules: {
                'strict': [2, "function"]
            },
            globals: {
                'angular': false,
                '_': false
            },
            env: {
                'browser': true
            }
        }))
        .pipe(eslint.failOnError());
});

gulp.task('js-injection', function () {
    return gulp.src('./src/main/resources/static/index/index.html')
        .pipe(inject(gulp.src(['./src/main/resources/static/**/*.js', '!./src/main/resources/static/scripts.js'], {read: false}),
            {ignorePath: 'src/main/resources/static'}))
        .pipe(gulp.dest('./src/main/resources/static'));
});


gulp.task('refresh', function () {
    return gulp.src(['src/main/resources/static/**'])
        .pipe(gulp.dest('target/classes/static'));
});

gulp.task('e2etests.local:run', ['e2etests:webdriver_manager_update'], function(cb) {
    var suiteArg = args.suite===undefined ? [] : ['--suite', args.suite];
    gulp.src(['src/test/resources/e2e/scenarios/**/*Spec.js'], { read:false })
        .pipe(gp.protractor({
            configFile: './src/test/resources/e2e/protractor.conf.js',
            args: ['--baseUrl', 'http://localhost:9000/'].concat(suiteArg)
        })).on('error', function(e) {
            if(isCI) {
                throw e;
            } else {
                console.log(e);
            }
            cb();
        }).on('end', function() {
            cb();
        });
});

gulp.task('e2etests.dev:run', ['e2etests:webdriver_manager_update'], function(cb) {

    gulp.src(['src/test/resources/e2e/scenarios/**/*Spec.js'], { read:false })
        .pipe(gp.protractor({
            configFile: './src/test/resources/e2e/protractor.conf.js',
            args: ['--baseUrl', 'http://10.9.7.11:9000/',
                    '--seleniumAddress', 'http://10.9.7.11:4444/wd/hub']
        })).on('error', function(e) {
            if(isCI) {
                throw e;
            } else {
                console.log(e);
            }
            cb();
        }).on('end', function() {
            cb();
        });
});

gulp.task('e2etests:webdriver_manager_update', gp.webdriver_update);

gulp.task('compileSass', function() {
    gulp.watch('./src/main/resources/static/assets/sass/*.scss', ['compass']);
});

gulp.task('watchStatic', function() {
    gulp.watch('./src/main/resources/static/**', ['refresh']);
});

gulp.task('webserver', function() {
  connect.server({
	  root: './src/main/resources/static/',
	  livereload: true,
	  fallback: './src/main/resources/static/index.html'
  });
});

gulp.task('livereload', function() {
  gulp.src([htmlSrc, jsSrc])
    .pipe(connect.reload());
});

gulp.task('watchsrc', function() {
  gulp.watch([htmlSrc, jsSrc], ['livereload']);
});

gulp.task('default', ['scripts',  'js-injection']);
gulp.task('cnr', ['default', 'refresh']);

gulp.task('dev', ['scripts','js-injection', 'webserver', 'livereload', 'watchsrc']);