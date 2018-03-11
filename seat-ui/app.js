const express = require('express')
const htmls = require('ejs')

const app = express()

app.use(express.static('js'))
app.set('view engine', 'ejs');
 

app.get('/', function (req, res) {
		
	 console.log("process.env.HOSTNAME:["+process.env.ROUTE_HOSTNAME+"]");
   //res.sendFile( __dirname + "/" + "index.html" ,{testenv: process.env.HOSTNAME || 'http://localhost:3000'});
   
   res.render("index",{routeurl: process.env.ROUTE_HOSTNAME || 'http://localhost:3000'}) 
   })

app.listen(3000, () => console.log('Example app listening on port 3000!'))