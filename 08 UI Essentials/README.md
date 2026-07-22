
Lab Setup
--------------------------------------

    Chrome (or any other browser)
    VSCode (or any other text editor)
    NodeJS 22.x or above

HTML - Hyper Text Mark Up Language
------------------------------------------------------------------------

    is used to write a document that can be shared across HTTP protocol.

    Hyper Text (Hyper Media) is a collection of plain text, formated text, graphcis and links.
    
    Mark up is a practise used in literature...

        WWW (World wide web) is quite ancient now.

        "This is your first day. Have a blast" said my manager.

        Vizag - the steel city - is my home town.

    SGML
    |
    |- HTML

    1. The entire document must have one and only one root element.
    2. Each element is repreented by a open and a close tag
    3. Each open tag must have a matching close tag.
    4. Each tag can have additonal attribute and the valeus of the attributes msut go in dbl quotes 
        irrespective of the type of the data.

    The symbols used to mark up are called HTML elements.

    Each html element is made up of a opening tag + content + closing tag.

        <p id="para1"> This is a para element and para content goes here </p>

    a sample html document

        <!DOCTYPE html>
        <html>
            <head>
                <!-- meta data about the current page -->
            </head>
            <body>
                <!-- the page content -->
            </body>
        </html>
    
    Typography
        h1 to h6        heading elements                        block ele
        p               para                                    block ele
        b,u,i           bold, underlien and italisized          inline ele
        strong,em       symatic elements for bold and italics   inline ele   
        sup,sub                                                 inline ele

        div             container block element
        
        nav,header,section
        footer,article
        main,aside      symatic container block elements
        
        span            inline group element

        kbd, code,
        date, mail,
        address,phone   symatic inline group element

    Lists
        ul          unordered lists
            type        circle/dot/square
        ol          ordered lists
            type        1/a/i/I/A
        li          list item

    HTML Tables    
        table                   to create a table
                border          attribute takes 1 to 5
                width           attribute takes a value in px/%/em/pt/in/cm
                height          attribute takes a value in px/%/em/pt/in/cm
                cellspacing     attribute takes a valeu in px/%/em/pt/in/cm and controls the spacing between two 
                                cells
                cellpadding     attribute takes a valeu in px/%/em/pt/in/cm and controls the spacing between the 
                                cell border and its content

        caption                 sub-element to give a heading to the table
        thead                   sub-element of table element and holds the header rows
        tfoot                   sub-element of table element and holds the footer rows
        tbody                   sub-element of table element and holds the data rows
        tr                      table row is a sub element representing one row and can be used inside
                                thead / tbody / tfoot / table
        th                      table header cell, a sub-element of tr
        td                      table data cell, a sub-element of tr
                rowspan         attribute of th and td representing the number of rows to be occupied default is 1
                colspan         attribute of th and td representing the number of columns to be occupied default is 1

    HTML Media

        img         inline element that injects an image into the page and is a self-closing element
             src    attribute of img tag to hold the relative or absolute or virtual path 
                    of the image

        audio       inline element to inject an audio
        video       inline element to inject a video
        source      sub-element of audio and video to supply the audio file or video file
                    a single audio or video element can house anynumber of source tags
                    each represeting a different format of audio or video.
            src     attibute of source to hold the relative or absolute or virtual path 
                    of the audio or video file

        iframe      used to embed an external web page into our own web page.
            src     attribute to hold the web address of the webpage you wish to embed.

    HTML Links
        a           anchor tag 
            href    attribute takes the destination path.
            target  attribute takes _self/_blank/iframeName

    HTML Form Elements
        form                is used to group a set of fields that have to be submitted to a server program.
            action          attribute takes the path of the server program to which the data has to be submitted
            method          attribute that takes GET/POST
        
        label               used to carry a text related to a field like field names
            for             attribute takes th id of the input element to which the label is related to.

        input               used to create a form field control that enable inputting data
            type            text
                            file
                            password
                            checkbox
                            radio

                            number
                            range
                            decimal
                            email
                            date
                            datetime-locale
            
            id              attribute is used to give an id to the input element.
            name            attribute is sued to give a field name that is carried along with its value to the server.
            value           attribute takes the initial vbalue of the field

            required        attribute ensures that the field is given a value mandatly
            min             attribute that takes a minimum possible value for type="number/decimal/date"
            max             attribute that takes a maximum possible value for type="number/decimal/date"
            minlength       attribute that takes a minimum length of the value while type="text"
            maxlength       attribute that takes a maximum length of the value while type="text"
            pattern         attribute that takes a reglar expression to validate the value while type="text"

        textarea            used to accpet multi line text
        
        select              used to create a drop-down or a list box
            multiple        attribute makes the drop-down into a list enabling multiple option selection
         option             is a sub-element of select used to provide the options
            value           attribute of option tag that takes the value to be submitted if this option is selected

        button              used to create a push button
            type            submit      (defualt)
                            reset
                            button

    HTML Layouts and sematic elements

        <header>    - Defines a header for a document or a section
        <nav>       - Defines a set of navigation links
        <section>   - Defines a section in a document
        <article>   - Defines an independent, self-contained content
        <aside>     - Defines content aside from the content (like a sidebar)
        <footer>    - Defines a footer for a document or a section
        <details>   - Defines additional details that the user can open and close on demand
        <summary>   - Defines a heading for the <details> element
        <figure>    - Defines a self-contained content, like illustrations, diagrams, photos, code listings..etc
        <figcaption>- Defines a caption for a <figure> element.
        <time>	    - Defines a date/time

    HTML entities

        Result	        Description	            Entity Name	    Entity Number
        =============================================================================
        non-breaking    space	                &nbsp;	        &#160;
        <	            less than	            &lt;	        &#60;
        >	            greater than	        &gt;	        &#62;
        &	            ampersand	            &amp;	        &#38;
        "	            double quotation mark	&quot;      	&#34;
        '	            single quotation mark 	&apos;      	&#39;
        ¢	            cent	                &cent;	        &#162;
        £	            pound	                &pound;	        &#163;
        ¥	            yen	                    &yen;	        &#165;
        €	            euro	                &euro;	        &#8364;
        ©	            copyright	            &copy;	        &#169;
        ®	            registered trademark	&reg;	        &#174;
    
CSS 3 
-------------------------------------------------------------------------------------
    CSS 3 - Intro
    -----------------------------------

        Cascading Style Sheet

        purpose is to provide styling to an html document.

        + CSS offers common uniform properties unlike html
        + CSS can provide styling across grouped elements.
        + CSS can isolate styling from content. It adds to maintainability.

        Mode Of CSS
            Inline Style Sheets
            Embeded Style Sheets
            External Style Sheets
        
        Inline Style Sheet

            it is applied using 'style' attribute.
            Inline style sheet is the least used method. It is applied only to the element
            on which it is written. 

            <tagName style="css-property:value;css-property:value;">
                content
            </tagName>

            <p style="text-align:justified;font-size:11pts;">
                this is a apra.
            </p>

        Embeded Style Sheet

            it is applied using a tag called <style></style>, style elements
            is a sub-element of <head></head>

            <head>
                <style>
                    selector {
                        css-property:value;
                        css-property:value;
                    }
                </style>
            </head>

        External Style Sheet

            style is defiend in a seperate file with extension .css
            and that cna be linked to any number of html pages using
                
                <link href="fileName.css" rel="stylesheet" />
        
        Selector is a string that qualfies the elements to be applied with the style.

            Tag Name Selector, Attribute Selector, Class Selector, ID Selector, Pseudo Selector           

            1. Tag Name
                    each tag name itself is a selector.
                    
                    tagName{
                        css-property:value; 
                    }

                    p{
                        text-align:center;
                    }

            2. Attribute
                    attribute selector groups elements having the mentioned attribute.
                    
                    [attributeName]{
                        css-property:value;
                    }

                    [src] {
                        width:50%;
                    }

            3. Class

                    is any usr defiend string. that should start with dot(.)
                    to apply the class on a element the element class attribute 
                    should be assigned with the class name.

                    .className{
                        css-property:value;
                    }

                    <tagName class="className1 className2">
                    </tagName>

                    .important {
                        background-color:#00000;
                        color:#ffffff;
                    }

                    .highlight{
                        border:1px solid white;
                    }

                    <p class="important"></p>

                    <h3 class="important highlight"></h3>

                    <tr class="highlight"></tr>

            4. Id
                    if a style swhould be applied to an elemnt hving 
                    a specific id, then id selector is used.

                    #id{
                        css-property:value;
                    }

            5. Psuedo
             

    CSS - unit of measure
    ------------------------------------

        absolute		(irrespective of screen size)
            in
            mm
            cm
            pt		1 in = 72 pt
            pc		1 in = 6  pc   1 pc = 12 pt
        
        relative 	(to screen size)
        
            px
            
            %
            
            em			relative the font-size
                        1 em = complete font-size
                                            
                        font-size: 12pt;
                        width: 8em;	8*12pt = 96pt;
                        
            vh			1% of viewport height
            vw			1% of viewport width
            vmin		min of vh or vw

    CSS Box Model Properties
    -----------------------------------
        margin
        margin-top
        margin-bottom
        margin-left
        margin-right

        border              border-size border-style border-color
        border-top
        border-bottom
        border-left
        border-right

        padding             space between the content and the elemnt border
        padding-top
        padding-bottom
        padding-left
        padding-right

        border-radius       radius of the corners of the element
        border-top-left-radius
        border-top-right-radius
        border-bottom-left-radius
        border-bottom-right-radius

        width
        height
        min-width
        min-height
        max-width
        max-height

        display             inline/block/inline-block

        position           relative / absolute

        top
        left
        bottom
        right
        z-index

    CSS background color and image related properties
    ----------------------------------------------
        background-color
        color
        background-image
        background-repeat       no-repeat/repeat/repeat-x/repeat-y
        background-position
        background-attachment    fixed/scroll
              
    CSS font related properties
    -----------------------------------
        font-family
        font-size
        font-variant		normal or small-caps
        font-weight			bold or bolder or boldest 
                            or any number like 300,400,500..etc
        font-style			italic or oblique
        
    CSS Text related Proeprties
    -----------------------------------
        color
        direction
        letter-spacing
        word-spacing
        text-align
        text-indent
        text-decoration		none | underline | line-through | overline
        
    table related css prperties
    ------------------------------------
        border-collapse			collapse or seperate
        border-spacing
        caption-side			top left right bottom
        
    CSS list related properties
    ----------------------------------

        list-style-type			disc	circle	square	none	for ul
                                decimal							for ol
                                decimal-leading-zero	
                                lower-alpha
                                upper-alpha
                                lower-roman
                                upper-roman
        list-style-position		outside or inside
        list-style-image:		url('imgs/note.png')
        marker-offset
        
    controlling scroll bars
    ------------------------------------
        
        overflow
                    visible
                    scroll
                    auto
                    hidden
                
    CSS Operators
    -------------------------------------
        s1		applies to elements that have s1 as tag name
        .s1		applies to elements that have s1 as class
        #s1		applies to elements that have s1 as id

        s1,s2	applies to elements that match both selectors
        s1 s2	applies to all elements that match s2 and are inside s1
        s1>s2	applies to all elements that match s2 whose parent is s1
        s1+s2	applies to all elements that match s2 and are immediately after s1
        s1~s2	applies to every element that match s2 and is preceded by s1

                        applies to all elements that have
                        ------------------------------------
        [s1]			 s1 as attribute
        [s1='v1']		 s1 attrib with v1 value
        [s1~='v1']		 s1 attrib value containes word v1
        [s1*='v1']		 s1 attrib value containes word v1
        [s1!='v1']		 s1 attrib value not equal to v1
        [s1^='v1']		 s1 attrib value starts with the word v1
        [s1$='v1']		 s1 attrib value ends with the word v1
       
        Psuedo selectors
        ----------------------------------------------------------------------------
        input:enabled	Selects every enabled <input> element
        p:first-child	Selects every <p> element that is the first child 
                        of its parent
        p::first-letter	Selects the first letter of every <p> element
        p::first-line	Selects the first line of every <p> element
        p:first-of-type	Selects every <p> element that is the first <p> 
                        element of its parent
        input:focus		Selects the input element which has focus
        a:hover			Selects links on mouse over
        input:in-range	Selects input elements with a value within a specified range

        input:indeterminate		
                        Selects input elements that are in an indeterminate state

        input:invalid	Selects all input elements with an invalid value
        input:optional	Selects input elements with no "required" attribute

        p:last-child	Selects every <p> element that is the last child of its parent
        p:last-of-type	Selects every <p> element that is the last <p> element of its parent

        a:link			Selects all unvisited links
        :not(p)			Selects every element that is not a <p> element
        p:nth-child(2)	Selects every <p> element that is the second child of its parent
        p:nth-last-child(2)	
                        Selects every <p> element that is the second child of its parent, 
                        counting from the last child

        p:nth-last-of-type(2)	
                        Selects every <p> element that is the second <p> element of its parent, 
                        counting from the last child
        p:nth-of-type(2)	
                        Selects every <p> element that is the second <p> element of its parent

    Layouts and Responsive Design
    ------------------------------------------------------------------------------------------------------
        1. Layout Containers

            In HTML, elements generally fall into two categories: **Block** and **Inline**. 
            Understanding these is the starting point for any layout.

            Block-level (`<div>`, `<h1>`, `<p>`, `<section>`):
                * Always starts on a new line.
                * Automatically takes up the full width available (100% of the parent).
                * Respects all sides of the box model (width, height, padding, margin).

            Inline (`<span>`, `<a>`, `<img>`):
                * Does **not** start on a new line; it sits side-by-side with other content.
                * Width and height properties are ignored.
                * Only takes up as much width as the content inside it.

            One can change this behavior using the `display` property (e.g., `display: inline-block;` allows an element to sit in a row but still respect width/height).
        
        2. Floats & Clearfix

            Before Flexbox and Grid, `float` was the primary way to create columns. While less common today.

            * The Float:    Moving an element to the `left` or `right`, 
                            allowing text and other elements to wrap around it.

            * The Problem:  When we float children inside a parent container, 
                            the parent "collapses" (its height becomes 0) because it no longer "sees" the floated children as being inside it.

            * The Clearfix: A CSS hack used to force the parent to expand and contain its floated children.

                .container::after {
                    content: "";
                    display: table;
                    clear: both;
                }

        3. Flexbox Basics

            Flexbox is a one-dimensional layout method for arranging items in rows or columns.
                * Flex Container:   The parent element (`display: flex;`).
                * Flex Items:       The immediate children of that parent.

            Main Alignment Properties:
                * `justify-content`: Aligns items on the **Main Axis** (Horizontal by default).
                * `align-items`: Aligns items on the **Cross Axis** (Vertical by default).
                * `flex-wrap`: Determines if items should stay on one line or wrap to the next if space runs out.
        
        4. Media Queries & Breakpoints

            Media queries allow you to apply CSS only when certain conditions are met, such as a specific screen width.
                * Syntax: `@media (min-width: 768px) { ... }`
                * Breakpoints: 
                    The specific widths where the layout "breaks" or needs to change. Common standards:
                        * Mobile: Up to 480px
                        * Tablet: 481px to 768px
                        * Desktop: 769px and above
            
            Responsive Typography & Images

            * Typography:   Use relative units like `rem` or `em` instead of `px`. 
                            We can also use Viewport Width units: `font-size: 5vw;`.
            * Images:       To prevent images from "overflowing" their container on small screens, always use:

                img {
                    max-width: 100%;
                    height: auto;
                }
        
        5. Mobile-First Design Concept

            Mobile-first is a design strategy where you start by styling for the smallest screens first and then use media queries to add complexity as the screen gets larger.

            Why use it?
                1. Performance: Mobile devices often have slower connections; loading simpler CSS first is faster.
                2. Focus: It forces you to prioritize the most important content.
                3. Cleaner Code: We use `min-width` queries to "add" styles, which is generally more intuitive than "removing" or overriding styles with `max-width`.

            Example Workflow:

                /* 1. Base styles (Mobile) */
                .container {
                    width: 100%;
                    padding: 10px;
                }

                /* 2. Tablet adjustments */
                @media (min-width: 768px) {
                    .container {
                        width: 80%;
                    }
                }

                /* 3. Desktop adjustments */
                @media (min-width: 1024px) {
                    .container {
                        width: 1200px;
                    }
                }

HTML & CSS Assignment
---------------------------------------------------------------------------

    Digital resume:
        Banner Section
                Photo
                Name
                Contact details
        Skill Set
        Technical Qualifications
        Academic Qualification
        Experience And Recent Projects
        Achivments And Publications
        Personal Details

JavaScript (ES6)
---------------------------------------------------------------------------

    Javascript Basics

            is a scripting language that can execute its scripts
            both on a browser and in a stand alone mode too.

            we need a javascript runtime to execute the script
            without a browser. And NodeJS is one such runtime.

            1. javascript inherits it syntx from c and cpp,hence case sensitive
            2. is a dynamically typed language
                a. variables need not be declared
                b. even if we declare using var/let/const keywords, we dont specfiy the datatype
                c. the type of a variable is decided after it is assigned a value
                d. a variable can hold data of different types.                
                        number,strings,null,array,function,undefined

        Datatypes
        Variables and scopes (let,var), operators
        Control Structures
        Program Flows
        String, Math, Date

        String Methods
            String length
            String slice()
            String substring()
            String substr()
            String replace()
            String replaceAll()
            String toUpperCase()
            String toLowerCase()
            String concat()
            String trim()
            String trimStart()
            String trimEnd()
            String padStart()
            String padEnd()
            String charAt()
            String charCodeAt()
            String split()
            String indexOf()
            String lastIndexOf()
            String search()
            String match()
            String matchAll()
            String includes()
            String startsWith()
            String endsWith()

        Math Methods
            Math.E        // returns Euler's number
            Math.PI       // returns PI
            Math.SQRT2    // returns the square root of 2
            Math.SQRT1_2  // returns the square root of 1/2
            Math.LN2      // returns the natural logarithm of 2
            Math.LN10     // returns the natural logarithm of 10
            Math.LOG2E    // returns base 2 logarithm of E
            Math.LOG10E   // returns base 10 logarithm of E
            Math.round(x)	Returns x rounded to its nearest integer
            Math.ceil(x)	Returns x rounded up to its nearest integer
            Math.floor(x)	Returns x rounded down to its nearest integer
            Math.trunc(x)	Returns the integer part of x (new in ES6)
            abs(x)	        Returns the absolute value of x
            acos(x)	        Returns the arccosine of x, in radians
            acosh(x)	    Returns the hyperbolic arccosine of x
            asin(x)	        Returns the arcsine of x, in radians
            asinh(x)	    Returns the hyperbolic arcsine of x
            atan(x)	        Returns the arctangent of x as a numeric value between -PI/2 and PI/2 radians
            atan2(y, x)	    Returns the arctangent of the quotient of its arguments
            atanh(x)	    Returns the hyperbolic arctangent of x
            cbrt(x)	        Returns the cubic root of x
            ceil(x)	        Returns x, rounded upwards to the nearest integer
            cos(x)	        Returns the cosine of x (x is in radians)
            cosh(x)	        Returns the hyperbolic cosine of x
            exp(x)	        Returns the value of Ex
            floor(x)	    Returns x, rounded downwards to the nearest integer
            log(x)	        Returns the natural logarithm (base E) of x
            max(x, y, z, ..., n)	Returns the number with the highest value
            min(x, y, z, ..., n)	Returns the number with the lowest value
            pow(x, y)	    Returns the value of x to the power of y
            random()	    Returns a random number between 0 and 1
            round(x)	    Rounds x to the nearest integer
            sign(x)	        Returns if x is negative, null or positive (-1, 0, 1)
            sin(x)	    Returns the sine of x (x is in radians)
            sinh(x)	    Returns the hyperbolic sine of x
            sqrt(x)	    Returns the square root of x
            tan(x)	    Returns the tangent of an angle
            tanh(x)	    Returns the hyperbolic tangent of a number
            trunc(x)	Returns the integer part of a number (x)

        
    Javascript OOPs+
    Javascript Functions
        Arrow Functions
        Memeber Functions and this keyword
        call and apply
        call backs and closures

    Javascript Arrays
        1. let arr = [];
        2. let arr = new Array();

        3. arrays are not homoginous
        4. arrays are not fixed in size

        Array properties
            arr.length

        Array functions
            arr.push(ele);
            arr.pop();
            arr.concat(anotherArr);
            arr.splice(startIndex,deleteCount);

        Array Prototype Functions
            
            arr.forEach(ele => {}); 
                //will execute the given callback function on each and every ele of the array
            
            arr.sort((ele1,ele2) => { return -1 if ele1<ele2 or return 1 if ele1>ele2 else return 0 })
                //will sort the array and uses the given callback function to compare.
                //and the sort function returns the sorted array.

            arr.map(ele => { write code to transform ele to anuthing else and return it })
                //the map function will execute the givne callback on each ele
                //of the array and returns a new array containing the transformed values.

                let arr = [1,2,3,4,5];
                let arr2 = arr.map( ele => ele*ele);

                arr2 will be [1,4,9,16,25]

            arr.reduce((ele1,ele2) => { return any binary output of ele1 and ele2 })
                //the reduce function will execute the passed binary operation on
                //first two eles of the array, and then that result paired with third ele
                //of the array is again passed to the binary operation and son on...
                //until no more eles left and final reduced value is returned.

                let arr = [1,2,3,4,5];
                let sum = (n1,n2) => n1+n2;

                arr.reduce(sum); 
                    //is as same as 
                    // sum(sum(sum(sum(arr[0],arr[1]),arr[2]),arr[3]),arr[4])
                    // which is 15
           
    ES6 Enhancements
        Modules
        Template Literals
        Arrow Functions
        Destructuring
        Spread and Rest operators
    
    Javascript Asynchronous Programming
        setInterval
        clearInterval
        setTimeout
        clearTimeout

        async, await and Promise

            a Promise is a class whose object accepts a job-function.
            a job-function accepts two callbacks resolve and resposne and
                excutes a task asynchronously. it invokes resilve incase of
                successful completion of the job and invoke reject incase of error.

            let p = new Promise( 
                (resolve,reject) => {
                    ....async code will go
                        if done call resolve
                        if err call reject
                }
            );

            p.then(
                () => { to respond if job done},
                () => { to respond if job got error}
            );


            define a class that takes a 'count' as a field in its constructor,
            it has to offer a function generateRandomNumbers which returns a promise.
            the generateRandomNumbers function shall generate 'count' number of random
            number using Math.random. the random number must be of the range 0 to 100.
            These numbers must be printed asynchronously and the promise shall emit the
            sum of the random number after resolving and shall reject with an error if the
            count is negative...

NodeJS

    Working with a NodeJS App

        md app-name
        cd app-name
        npm init -y

    Node Package Manager
        is the build tool packed along with nodejs

        'package.json' is the build descriptor file.
            will contain the project meta-data (name,version,author,keyword ..etc)
            and the list of dependencies and list of npm-scripts.

        npm i package-name@version
            is used to install a third party package into our nodejs app.
            'app-name/node_modules' will contain the downloaded files.
        
        npm i -g package-name@version
            is used to install a third party package globally on our machine.
            '<userFolder>/node_modules' will contain the downloaded files.

        npm uninstall package-name
        
        npm test                executes the 'test' script
        npm start               executes the 'start' script
        npm run build           executes the 'build' script
        npm run custom-script   executes the 'custom-script' script

    Javascript Modules

        whiule in native-javascript, we had no modularizaton.

        RequireJS and CommonJs were two different standards introduced by third-party players
        for modularization.

        ES6 - ECMAScript Modules (esm)

        Each .js file is a module

        Each artifact of a Javascript module is private to that module.

        'export' keyword is used to allow access to the artifacts of a module outside the module.

        'import' keyword is used to bring the artifacts from one module to another.

        'export default' is used to provide a default artifact of a module. 
        And a module can have only one such artifact. Default artifacts can be imported with out {}.

Typescript

    Typescript = JavaScript + Types

    Creating a ts-project:

        npm init -y
        npm i typescript 

        npx tsc --init

    Transpelling ( Compiling ) 

        npx tsc filename.ts 

    Datatypes

        number
        string
        boolean
        bigint

        symbol          //is used to handle UUID's 
                        const photoId:symbol = Symbol("someKey")

        void

    Special Datatypes

        any
                        const swap = (x:any,y:any) => {
                            //developer is sure that no issues will occur 
                            //whatever may be the type of value of x.

                            var temp : any = x;
                            x = y;
                            y = temp;
                        }
        
        unknown                                
                        const len = (x:unknown)  => {
                            //developer is NOT SURE that an issue will NOT occur 
                            //based on the type of value of x.
                            //and for this reason, the type of 'x' has to be CHECKED before it is consumed
                            //and compiler 'tsc' will raise an error if the type checking is not done

                            if( typeof(x) === 'number'){
                                return `${x}`.length();
                            }else if( typeof(x) === 'string'){
                                return x.length();
                            }else {
                                throw "length may not be relevant for this value";
                            }
                        }

        undefined           the "salary" proeprty on a Consumer object is undefiend.
        null                the "referalBenifit" property on a Consumer object can be null.

    User Defined Data Types

        interface
            is a user defiend data type with methods that are not implemented.

            interface ComplexNumber{
                real:number;
                imaginary:number;
            }

            var z1 = {real:90,imaginary:-7}
            var z2 = {real:4,imaginary:-7}
            var z3 = {real:4,imaginary:-7,alpha:99}

            function addComplexNumbers(a:ComplexNumber,b:ComplexNumber){
                //code will go
                //z1 and z2 are acceptable valeus into this function but z3 is not.
            } 
            
            interface Shape2D {
                sides:number[];
                area : () => number; //methods with no impl;
            }

        class
            is a user defiend data type that has fields and methods.
            classes support access modifiers private,public and protected.

            class Employee {
                private empId:number;
                private fullName:string;
                
                constructor(empId:number,fullName:string,private sal:number){
                    this.empId=empId;                    
                    this.fullName=fullName;
                }

                travelAllowence(){
                    return this.sal*0.12;
                }

                houseRentAllowence(){
                    return this.sal*0.32;
                }

                totalSal(){
                    return this.sal + this.houseRentAllowence() + this.travelAllowence();
                }

            }

            var emp1 = new Employee(101,"Vamsy",89000)

        unions

            var temp : number | string ;

            temp = 90;
            temp = "high";
            temp = "low";
            temp = 47;

        aliases
            is another name given to a datatype.

            type num = number;

            var x : num;

            type alphaOrNumeric = number | string;

            var itemId:alphaOrNumeric;
            var temp:alphaOrNumeric;

        enums      
            is a set of possible values of a field.

            gender  can be eitehr Male or Female
            
            enum Gender {
                MALE, FEMALE
            }

            var gender : Gender;
            gender = Gender.MALE;

            enum Direction {
                UP, DOWN, LEFT, RIGHT
            }

            var d1 : Direction;
            d1 = Direction.UP;

        types

            type Point2D = {x:number,y:number};
            type Point3D = {x:number,y:number,z:number};


    Standard tsconfig

        {
            "compilerOptions": {
                "module": "commonjs",
                "esModuleInterop": true,
                "target": "es6",
                "moduleResolution": "node",
                "sourceMap": false,
                "outDir": "dist"
            },
            "lib": ["es2015"]
        }

    functions

        function funName(param1:type,param2:type) : returntype {

        }

        const funName = function(param1:type,param2:type) : returntype {

        }

    '!' and '?' operator
        
        function f(c:Consumer){
            var doorNum = c.address.doorNumber; //chance of null-error if address is null or undefined.
        }

        function f(c:Consumer){
            var doorNum = c.address!.doorNumber; //doorNum is assigned null if address is null or undefined           
        }

        function f(c:Consumer){
            var doorNum = c.address?.doorNumber; //doorNum is assigned undefined if address is null or undefined
        }

    '?' for optional fields

        interface Employee{
            empId:number;       //this field can not be undefiend or null
            fullName:String;    //this field can not be undefiend or null
            salary:String;      //this field can not be undefiend or null
            bonus?:number; //here bonus is an optional field or a field that can be undefined but not null
            laptopId:Symbol | null;  //this field can not be undefiend but can be null
        }

Statement Page 
-------------------------------------------------------------------------------------------

Develop a simple CRUD operations Page,using typescript:

    This page shall offer a dynamic statement management.

    id    DateOfTransaction      Header               Credit      Debit
    =============================================================================================
         [____________]     [____________________]   [________]   [________]   (ADD)
    =============================================================================================
    1.      01-Jan-2026         Salary                  80,000                  (EDIT) (REMOVE)
    2.      01-Jan-2026         Rent                                    5,000   (EDIT) (REMOVE)
    3.      [ 01-Jan-2026 ]     [Fuel               ]   [______]   [1,000     ] (SAVE) (CANCEL)
    =============================================================================================
                                Totals                  80,000          6,000
    =============================================================================================
                                Balance                                         74,000                     

    stmt-app-ts
        model             
            Txn.ts
                export interface Txn {
                    id:number;
                    header:string;
                    txnType:"CREDIT"|"DEBIT";
                    amount:string;
                    txnDate:string;
                }                
        lib
            script.ts
                import type { Txn } from './Txn';

                var txns : Txn[] = [];               

                //dom-manipulation-event-handling-code
        
        styles
            style.css
        pages        
            index.html                        

    