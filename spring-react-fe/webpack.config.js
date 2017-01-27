var packageJSON = require('./package.json');
var path = require('path');
var webpack = require('webpack');

const PATHS = {
  build: path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', packageJSON.name)
  // build: path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version)
};

var node_dir = __dirname + '/node_modules';

module.exports = {
	entry: './app/app.js',
	devtool: 'sourcemaps',
	cache: true,
	debug: true,
	resolve: {
	   alias: {
	       'stompjs': node_dir + '/stompjs/lib/stomp.js',
	   }
	},
  output: {
		path: PATHS.build,
		filename: 'app-bundle.js',
		publicPath: '/assets'
	},
	devServer: {
	    // proxy: {
	    //   '/api': {
	    //     target: 'http://localhost:8090',
	    //     secure: false
	    //   }
	    // }
			proxy: [
				{
				  context: ['/api','/payroll'],
				  target: 'http://localhost:8090',
				  secure: false
				}
			]
	},
	module: {
			loaders: [
				{
					test: path.join(__dirname, '.'),
					exclude: /(node_modules)/,
					loader: 'babel-loader',
					query: {
							cacheDirectory: true,
							presets: ['es2015', 'react']
					}
				},
				{
	        test: /\.css$/,
	        loader: "style-loader!css-loader"
	      },
	      {
	        test: /\.png$/,
	        loader: "url-loader?limit=100000"
	      },
	      {
	        test: /\.jpg$/,
	        loader: "file-loader"
	      },
	      {
	        test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/,
	        loader: 'url?limit=10000&mimetype=application/font-woff'
	      },
	      {
	        test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
	        loader: 'url?limit=10000&mimetype=application/octet-stream'
	      },
	      {
	        test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
	        loader: 'file'
	      },
	      {
	        test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
	        loader: 'url?limit=10000&mimetype=image/svg+xml'
	      }
			]
	}
};
