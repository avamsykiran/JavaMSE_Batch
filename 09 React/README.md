ReactJS
----------------------------------------------------------------------------

    ReactJS is a javascript framework to develop SPA.

    SPA - Single Page Application

    SPA has only one html page 'index.html' and is backed with alot
    of javascript code. Any form events or hyper links or any other UI
    event is handled by this javascript and in respone to the event it generates
    html dynamically on the client side and replaces the content o the index page with
    the newly generated content.

    SPA uses json/xml to send or receive data from a rest-api.

        Dynamic Web Applications

            WevServer                                            WebClient / Browser
                Controller                  <-----------request1----
                (Servlet/Spring WebMVC)

                    after processing the req
                    it forwards 
                    it to a View (.jsp/thymeleaf)

                    the View will generate html-content
                                ---resp (html) ----------------->   the new page is loaded
                                                                    when any event happens,
                                                                        or when a form is submitted
                                                                        or when a link is clieked,
                                 <-----------request2----
                    after processing the req
                    it forwards 
                    it to a View (.jsp/thymeleaf)

                    the View will generate html-content
                                ---resp (html) ----------------->   the new page is loaded again


            1. html-content is generated on the server for everhy request.
            2. as many number of clients that much of work-load on server
            3. client can not show any page unless the server sends the html content in the resp.
        
        Single PAge Applications

            WevServer                                            WebClient / Browser
                
                spa-bundle              <-----------request1----
                    (index.html +
                        aLotOfCode.js +
                        styles.css)
                                
                                ---resp (spa-bundle) -----------------> index.html along with 
                                                                        the entire JS code is loaded on client

                                                                        when any event happens,
                                                                        or when a form is submitted
                                                                        or when a link is clicked,

                                                                        the javascript handles all of them.
                                                                        it generates html-content dynamically
                                                                        pon the client and repalces the content
                                                                        of the html page with the newly generated content
                                                                        from time to time.

                rest-api  <------data (.xml/.json)----------------->    spa-bundle


    An SPA is generally compsoed of Components. Each component is an custom developed
    html element.

    Components in react use JSX/TSX for generating html content dynamically.

    JavaScript/TypeScript eXtened Mark Up Language is an amulgamation of Javascript/Typescript
    and html.

    Create a reactjs app
    -----------------------------------------------------------

    npm create vite     create the project stucture for the selected framework
    npm i               will install all the dependencies listed in 'package.json'
    npm run dev         is the npm script to execute the app in developer mode


    JSX/TSX
    ----------------------

        .ts 
            var userName = "Vamsy";
            var pObj = document.createElement("p");
            pObj.innerText = "Hello " + userName

        .tsx
            var userName = "Vamsy";
            var pObj = <p> Hello {userName} </p>
            
        NOTE: the interpolation-expression shall return either a number or a string or a boolean or 
        a HtmlElement or an array of HtmlElements. (nothing else)

        .ts
            var friends = ["Vamsy","Vani","Venu","Vasanth"];
            var olObj = document.createElement("ol");
            
            friends.forEach( f => {
                let liObj = document.createElement("li");
                li.innerText = f;
                olObj.append(liObj);
            })
        
        .tsx
            var friends = ["Vamsy","Vani","Venu","Vasanth"];
            var olObj = (
                <ol>
                    { friends.map( f => <li>{f}</li> ) }
                </ol>
            );
    
        Rules:
            1. JSX/TSX is case sensitive.

            2. Html built-in elements are always written in lower-case

            3. Html attributes are always written in camelCase. 
                <td vertical-align="top"> </td>     //regular html
                <td verticalAlign="top"> </td>      //tsx

            4. Custom Html Elements / Components are written in PascalCase (init-caps).
                <header></header>   is a html element
                <Header></Header>   is a ReactJs Component

            5. 'class' attributes is not permitted as it is a keyword in js/ts, instead we use
                'className'


        Another Example (TSX)

            interface Employee {
                empId:number;
                fullName:string;
                salary:number;
            }

            var empList : Employee[] = [
                {empId:101,fullName:"Vamsy",salary:45000},
                {empId:102,fullName:"Varun",salary:35000},
                {empId:103,fullName:"Madhu",salary:25000},
                {empId:104,fullName:"Vijay",salary:40000},
                {empId:105,fullName:"Anjali",salary:40000}
            ];

            var empHtmlTable =(
                 <table>  
                    <thead>
                        <tr>
                            <th> Employee# </th>
                            <th> Name </th>
                            <th> Salary </th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            empList.map( e => (
                                <tr>
                                    <td> {e.empId} </td>
                                    <td> {e.fullName} </td>
                                    <td> {e.salary} </td>
                                </tr>
                                ) 
                            )
                        }                        
                    </tbody>
                 </table> 
            );

    ReactJS Components
    ----------------------------------------

        A Component is a custom developed html-element.

        In ReactJS a Component can be developed in three ways.

        (a) Class Components
        (b) Function Components
        (c) High Order Components

    Class Component
    ---------------------------------------

        Any class that extends React.Component is called a Class Component.

        From React.Component, a class component inherits
            1. state                                is a field that holda all the data related to a component
                                                    'state' is immutable. 'state' is initialized in the constructor. and it can be replaced using 'setState()'
                                                    method.

            2. render()                             is the method that return the html content for a component.
                                                    render() is reexecuted each tiem the 'state' gets replaced.

            3. setState()                           is the method used to repalce the 'state', and each time
                                                    setState() is called, render() follows.

            4. componentDidMount()
            5. componentDidUpdate()
            6. and such other life-cycle methods

        class Dashboard extends React.Component< {}, {appTitle:string} > {
            constructor(props:{}){
                super({});
                this.state = {
                    appTitle:"My First App"
                }
            }

            render(){
                return (
                    <h1> {this.state.appTitle} </h1>
                )
            }
        }

        <Dashboard />       renders     <h1>My First App</h1>

        type SimpleInterestCompStateType = {loanAmt:number,roi:number,timePeriod:number};
        class SimpleInterestComp extends React.Component< {}, SimpleInterestCompStateType > {
            constructor(props:{}){
                .......
            }

            render(){
                return (
                    ..,,,,,,,,,,,,,,,,
                )
            }
        }


    LifeCycle of a Class Component
    ---------------------------------------

        constructor()                   //state gets initialized here
            ↓
            render()                    //the html content is returned here
                ↓
                componentDidMount()     //this is used to execute a task immidiatly after first rendering
                    |                   //like loading data from rest-api ..etc.,     
                    ↓
                    ******************************************
                        the component will be idle
                  |→    until 'setState' is invoked by 
                  |     any means (an event or a form submiton..etc)
                  | **********************************************
                  |             ↓
                  |             render()                    //the updated html content is returned here
                  |                 ↓
                  |←--------------- componentDidUpdate()    //side-effect are handled.

    ReactJS 'props'
    --------------------------------------

        'props' short for properties is a mechanisim used by a 
        parent component to share data with a child component via attributes.

        class Banner extends React.Component< {appTitle:string} , {} > {
            constructor(props:{appTitle:string}) {
                super(props);
                this.state ={};
            }

            render(){
                return (
                    <h1> {this.props.appTitle} </h1>
                )
            }
        }

        class Dashboard extends React.Component< {} , {pendingTasks:number,completedTasks:number} > {
            constructor(props:{}) {
                super(props);
                this.state ={
                    pendingTasks:0,
                    completedTasks:0
                };
            }

            render(){
                return (
                    <section>
                        <Banner appTitle="My Task Schedular App" />
                        <div>
                            Pending Tasks: {this.state.pendingTasks}
                        </div>
                        <div>
                            Completed Tasks: {this.state.completedTasks}
                        </div>
                    </section>
                )
            }
        }
 
    Function Components
    -----------------------------------------------------------
        Function Component is any function that can return html-content.
        These components are initially called state-less components and were used
        to generate html-content that has no dependency on any state.

        const WelcomeSection = () => (
            <section>
                <h3>Welcome All, Good to see you usign my App. </h3>
            </section>
        );

        <WelcomeSection />

        1. Function Components are highly light weight compared to Class Components.
        2. These are not attached to the React Framework directly and hence are easy to test.
        3. These can receive 'props' as an argument.
        
            const PageHeader = (props : {title:string} ) => (
                <header>
                    <h3>{props.title} </h3>
                </header>
            );

            <PageHeader title="App Name" />
        
        4. These do not have access to component state/ lifecycle methods. And hence are used to be called as
            state-less components.

    React Hooks
    -----------------------------------------------------------

        A hook is a function that is designed to provide additional features to a Function Component.
        
        A hook can be invoked anywhere inside a function component expect in a loop or conditonal statement or as part of any other expression block or return block.

        useState        is a hook that proivdes local state managemnet in a function component.
                        this hook return an array having two elements a reader and a writer in that order.

                        const [count,setCount] = useState<number>(0);

                        count       is the reader to the get the current value of the state field
                        setCount    is the writer to be sued to replace the value of count

                                setX(10);       will change the valeu of x to 10
                                
                                setX( currentValue => (expressionThatCanRecomputeAndReturnANewValue) );

                                setX( currentValue => currentValue*2 ); //the 'x' is doubled.

                        For Example
                                const [n,setN] = useState<number>(0);

                                setN(10)             //here, n value is 10

                                setN(n+1);
                                setN(n+1);
                                setN(n+1);
                                setN(n+1);           //here, n value will be 11 instead of 14

                                setN( cv => cv+1 );
                                setN( cv => cv+1 );
                                setN( cv => cv+1 );
                                setN( cv => cv+1 );  // here, n value will be 15

        useEffect       is a hook designed to work as an alternate to componentDidMount and componentDidUpdate 
                        in a function components.

                        useEffect(callBack)         
                            this callBack is executed everytime after the component render
                        
                        useEffect(callBack,anEmptyArray)         
                            this callBack is executed only once after the component rendered for the first time
                            equivalent to componentDidMount
                        
                        useEffect(callBack,anArrayHavingStateFields)         
                            this callBack is executed eachtime after the component is rendered and atleast one of the
                            fields in the array are modified
                            equivalent to componentDidUpdate

                            cosnt loanComponent = () => {
                                cosnt [p,setP] = useState<number>(1000);
                                cosnt [t,setT] = useState<number>(12);
                                cosnt [r,setR] = useState<number>(1.5);
                                cosnt [si,setSI] = useState<number>(180);

                                useEffect( () => {
                                    setSI( (p*t*r)/100 );
                                } , [p,t,r] );

                                retrun (
                                    /*here goes a form to accept the valeus for p , t, r
                                    and display the value of si.*/
                                    <section>
                                    </section>
                                );
                            };

    Integrating Bootstrap with React
    -----------------------------------------------------------

        npm i bootstrap

        node_modules
            |- bootstrap/dist/css/bootstrap.min.css
            |- bootstrap/dist/js/bootstrap.bundle.js

        import these two files in the main.tsx

    Integrating Bootstrap with ReactBootstrap
    -----------------------------------------------------------

        npm i bootstrap react-bootstrap

    Bootstrap Basics
    -----------------------------------------------------------

        is a styling library that offers resposnive web design and out-of-the-box components
        like progress-bar, navbar, containers, pills, cards, badges, ..etc.,.

        1. Bootstrap Grid System
                bootstrap offers two elastic containers
                        container-fluid     is a full-width container   <div class="contaienr-fluid"> </div>
                        container          is a page-width container   <div class="contaienr"> </div>

                bootstrap offers four sizes of screens
                    xs          extra samll for mobile screens
                    sm          small for tablets
                    md          md for larger tablets or smaller monitors
                    lg          large screens

                bootstrap offers rows and cols to organize the content
                    a row can accomidate 12 units of width maximumm.

                    <div class="row">
                        <div class="col-1"> a column that occupies one unit of width </div>
                        <div class="col-2"> a column that occupies two units of width </div>
                        <div class="col-6"> a column that occupies two six of width </div>
                        <div class="col"> a column that occupies all the left over width (in this example 3 units) </div>
                    </div>

                    col-xs-2        this occupies 2 units of width starting from extra-small devices

                    col-sm-2        this occupies 2 units of width starting from small devices only and
                                    occupies full-screen width on extra-small devices.

                    col-md-2        this occupies 2 units of width starting from medium devices only and
                                    occupies full-screen width on extra-small and small devices.                                    

        2. Bootstrap Utilities

                margin control css classes
                        m-0,m-1,m-2,m-3,m-4,m-5
                        mb-1, mt-1, ms-1 (left margin), me-1 (right margin)
                
                padding control css classes
                        p-0,p-1,p-2,p-3,p-4,p-5
                        pb-1, pt-1, ps-1 (left margin), pe-1 (right margin)

                border control css classes
                    border-0 to border-5                                                    controls border thiuckness
                    border-primary, border-secondary, border-danger, border-info            controls border color
                    border-bootom-0 ...edtc.,

                background-color control css classes
                    bg-info, bg-dnager, bg-primary ...etc.,

        3. Bootstrap Components

                buttons
                forms
                input-elements

                pills
                badges
                message-boxes

                nav bars
                accordians
                models  (dialog boxes)
                tabs 
                cards

    Working with Forms in reactjs
    -----------------------------------------------------------

        Controlled Components

            here, a form-input-control is directly mapped to a state-field of a component.
            this promots single-source-of-truth. 

            const Welcome = () => {

                const [userName,setUserName] = useState<string>("");
                
                return (
                    <h3>Welcome! {userName} </h3>

                    <form>
                        <label>User Name: </label>
                        <input type="text" value={userName} onChange={ e => setUserName(e.target.value) } />
                    </form>
                );
            }

        UnControlled Components

            here, we create something called 'ref' s , where
            each ref is mapped to one form-input-controlled, which
            later cna be used to extract data from the fomr-input-control.

        Controlled Components are 99% prefered than UnControlled component inspiteof
        a little complexity involved, and thats due to the memory-weight.

    Form Hook Library
    -----------------------------------------------------------

        this is a library that offer a hook called "useForm"

        useForm     this hook takes inital/default values of the form-fields as arg.
                    returns a form-managemnt-object having methods like
                        register each input-field with the state-field
                        method to handle form-submition
                        ..etc.,

        npm i react-hook-form

    YUP Library for form validations
    -----------------------------------------------------------

        is a library that is used force an entity-schema (validation rules and default valeu on
        to a form)

            const validationSchema = Yup.object().shape({
                username: Yup.string()
                    .required('Username is required')
                    .min(3, 'Username must be at least 3 characters'),
                email: Yup.string()
                    .required('Email is required')
                    .email('Invalid email format'),
                password: Yup.string()
                    .required('Password is required')
                    .min(6, 'Password must be at least 6 characters'),
                confirmPassword: Yup.string()
                    .required('Please confirm your password')
                    // Use oneOf to match another field
                    .oneOf([Yup.ref('password'), null], 'Passwords must match'),
                acceptTerms: Yup.boolean()
                    .oneOf([true], 'You must accept the terms and conditions')
            });
                
        @hookform/resolvers     is another library to bridge between form-hook and yup.

            const {
                register,
                handleSubmit,
                formState: { errors },
            } = useForm({
                resolver: yupResolver(validationSchema), // Connects Yup to React Hook Form
                mode: 'onTouched', // Validates when a user leaves an input
            });
        
        npm i react-hook-form yup @hookform/resolvers

    React 'ref'
    -----------------------------------------------------------

        a ref is a react-handle assigned to any html-element that can later
        be sued to work with or manipulate the html-element programatically.

        'useRef' Hook

            cosnt ref1 = useRef()

            <p ref={ref1}>
                ....
            </p>

    Routing
    -----------------------------------------------------------

        is to map a component to a path, so tha that that component is rendered
        only when the mapped path is requested.

        npm i react-router

        <BrowserRouter>
            <!-- is common area, uncontrolled by router -->
            
            <Routes>
                <!--the is route area, controlled by router -->
                <Route path="path1" element={<Component1 /> } />
                <Route path="path2" element={<Component2 /> } />
                <Route path="path3" element={<Component3 /> } />
            </Routes>

            <!-- is common area, uncontrolled by router -->
        </BrowserRouter>

        Link        is a built-in component used to create client-side working hyper-links
                    as 'a' is a server-side hyper-link
        
        Hooks
            useLocation()       returns the location object through which info like current-path can be accessed

            useParam()          returns an associative array of all path parameters and query parameters, if any

            useNavigate()       returns a method, that can be used to programatically navigate between components.

    State Management
    -----------------------------------------------------------

        writing or reading data at a place is called state management

            Local State Management
                1. maintaining state in each component.
                2. 'state' for class-comp and 'useState' for func-comp are used
                3. Local state remains accessable only tot hat component.

            Global or Application Level State Management
                1. the state maintaiend globally at the application level
                2. data in the global state is accessable to all the compo's in the app
                3. reactjs offers 'context-api' (is highly costly as it triggers deep-rendering for each change in the data)
                4. Redux - a thrid party library - offers global state management
                5. RTK (Redux Tool Kit) - is a refined version of Redux.
                    RTK is far easy to work with from the developer's point compared to workiong with Redux directly.

            Server-Side State Management
                1. the data is maitnaiend on the server
                2. the first option is rest-api
                3. the second option Query Language Tools like GraphQL / TanStack Query ...etc.,

    Global State Management using Redux ToolKit
    -----------------------------------------------------------

        Redux
            is an independent library used to maintain state globally and is 
            used by a varity of frameworks like Angular, React ...etc.,

            Redux Arch

            store       is the global-state that contians the entire data of the applciation.
                        an app can have only one store

                        whenever the data in the store gets modified it notifies the
                        relevent components automatically

            reducer(s)  is a function that modifies the data in the store when reqeusted
                        by a dispatch

            action      is a object or function that indicates
                            what-operation-has-to-be-done (refered as action-type)
                            what-is-the-data-needed-for-that-operation (refered as payload)

            store -------------------------------------------
                ↑               ↓                           ↓
                |               |                           |
                |               Component1                  |
                |               |                           Component2
                |               | dispatch(action)          |
                |               |                           | dispatch(action)
                |               |                           |
                reducer(s) ←---------------------------------

        Redux Tool Kit

            is an enchanced layer on redux to create and mange the store

            Slice           a slice refers to a piece of State.
                            a slice has initialState, reducers and asyncThunks.

                            initialState    is the initial piece of data
                            reducer         is a function that manipulates 
                                            data as per an incoming action
                            action          is an object that indictes an
                                            operation.
                            asyncThunks     is an asynchronous function that 
                                            is used for api calls.

            createSlice     is a function used to create a slice

                            const mySlice = createSlice({
                                name:'sliceName',
                                initalState,
                                reducers: {
                                    //list of reducer functions where 
                                    //each function has to accept currentState and action
                                    //and return modifiedState
                                },

                            });

                            once slice is created, actions are also auto-created (one action for each reducer).

            createAsyncThunk    is used create asynchrnous actiosn called 'thunks'
                                these thunks are uysed to make rest-api calls.

            configureStore  is a function used to link reducers with Store

                            export const store = configureStore({
                                reducer: {
                                    reducerLabel: myReducer,
                                },
                            });

        React-Redux
            is a bridge service between Redux/RTK and ReactJS.

            Provider        is a component that is sued wrap the Store on the app.

                                <Provider store={store}>
                                    <App />
                                </Provider>

            useSelector     is a hook used to extract required data from state.

            useDispatch     is a hook that return a 'dispatch' function that 
                            is used to send an action from a component to a reducer.            
        
        npm i @reduxjs/toolkit react-redux

        RTK Entity Adapter

            When managing data  in Redux , writing repetitive CRUD logic for every slice gets tedious.

            createEntityAdapter     is a built-in utility in Redux Toolkit (RTK) that completely automates this. 
                                    It provides a standardized state structure, pre-written high-performance reducers, 
                                    and highly optimized, memoized selectors out of the box.

                1. The Normalized State Structure

                    When you initialize an entity adapter, it forces a predictable shape on your state slice:

                    {
                        ids: ['id1', 'id2', 'id3'],
                        entities: {
                            id1: { id: 'id1', name: 'Item One' },
                            id2: { id: 'id2', name: 'Item Two' }
                        }
                    }
                
                    ids         An array of strings or numbers ensuring a consistent chronological or sorted order.
                    entities    A lookup object map allowing you to fetch any record in O(1) time without scanning an entire array.
                
                2. Setting Up an Entity Adapter with TypeScript

                    import { createSlice, createEntityAdapter, PayloadAction } from '@reduxjs/toolkit';
                    import { RootState } from '../../app/store';

                    // 1. Define your data model
                    interface Book {
                        id: string;
                        title: string;
                        author: string;
                    }

                    // 2. Initialize the adapter
                    const booksAdapter = createEntityAdapter<Book>({
                        // Optional: Sort books alphabetically by title
                        sortComparer: (a, b) => a.title.localeCompare(b.title),
                        // Optional: If your unique identifier is named something other than 'id' (e.g., 'bookId')
                        // selectId: (book) => book.bookId, 
                    });

                    // 3. Generate initial state: This automatically creates { ids: [], entities: {} }
                    const initialState = booksAdapter.getInitialState({
                        loadingStatus: 'idle', // You can add custom, non-entity state fields here!
                    });

                    const booksSlice = createSlice({
                        name: 'books',
                        initialState,
                        reducers: {
                            // 4. Use adapter-provided CRUD reducers directly!
                            bookAdded: booksAdapter.addOne,
                            bookUpdated: booksAdapter.updateOne,
                            bookRemoved: booksAdapter.removeOne,
                        },
                    });

                    export const { bookAdded, booksReceived, bookUpdated, bookRemoved } = booksSlice.actions;
                    export default booksSlice.reducer;

                3. The Auto-Generated Selectors

                    One of the best features of `createEntityAdapter` is that it generates pre-optimized, memoized selectors. 
                        
                    // Extract the selectors and point them to where this slice lives in your RootState
                    export const {
                        selectAll: selectAllBooks,         // Returns an array of all books, correctly sorted
                        selectById: selectBookById,       // Returns a single book by ID
                        selectIds: selectBookIds,         // Returns just the array of IDs
                        selectTotal: selectTotalBooks,     // Returns an integer representing total records
                    } = booksAdapter.getSelectors((state: RootState) => state.books);

                    Usage in a React Component:
                        import { useSelector } from 'react-redux';
                        import { selectAllBooks, selectBookById } from './booksSlice';

                        export function BookList() {
                            const allBooks = useSelector(selectAllBooks); // Type: Book[]
                            
                            // No custom selectors required for lookups:
                            const singleBook = useSelector((state) => selectBookById(state, 'book_123')); 
                            
                            return (
                                // ... render logic
                            );
                        }

                4. Built-in Reducer Methods Reference

                    The adapter provides a specific lexicon of mutation helpers depending on exactly what we want to do with our store:
                
                    addOne(state, action)       Adds a single record. Does nothing if the ID already exists. 
                    addMany(state, action)      Adds multiple records. 
                    setOne(state, action)       Adds a record, or *completely overwrites* it if it already exists. 
                    setAll(state, action)       Clears out the entire collection and replaces it with the new records. 
                    setMany(state, action)      Adds or overwrites multiple records. 
                    updateOne(state, action)    Updates fields on a record. Expects `{ id, changes }` in the payload. 
                    updateMany(state, action)   Updates multiple specific records at once. 
                    upsertOne(state, action)    If the item exists, updates it. If it doesn't, inserts it. 
                    upsertMany(state, action)   Upserts an array of records. 
                    removeOne(state, action)    Deletes a record by its ID string/number payload. 
                    removeMany(state, action)   Deletes multiple records based on an array of IDs. 
                    removeAll(state)            Completely empties the `ids` array and `entities` map.                

    Working with 'axios' to make rest-api calls
    ------------------------------------------------

        Instalaltion

            npm i axios

        Api Requests from axios module

            axios.get('https://api.yourdomain.com/v1/books') : Promise<AxiosResponse>
            axios.put('https://api.yourdomain.com/v1/books',reqBody) : Promise<AxiosResponse>
            axios.post('https://api.yourdomain.com/v1/books',reqBody) : Promise<AxiosResponse>
            axios.delete('https://api.yourdomain.com/v1/books/101') : Promise<AxiosResponse>

        Using an axios Object

            const apiClient = axios.create({
                baseURL: 'https://api.yourdomain.com/v1',
                timeout: 10000, // Aborts request if it takes longer than 10 seconds
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            });

            apiClient.get('/books') : Promise<AxiosResponse>
            apiClient.put('/books',reqBody) : Promise<AxiosResponse>
            apiClient.post('/books',reqBody) : Promise<AxiosResponse>
            apiClient.delete('/books/101') : Promise<AxiosResponse>

        Interceptors

            an interceptor is a piece of task that excutes either before each request is send
            or after each response is received

            reqeust-interceptor
                apiClient.interceptors.request.use(
                    (config) => {
                        //interceptor task goes here like attaching a jwt token to a req ..etc.,
                        return config;
                    }
                );
            
            response-interceptor
                    apiClient.interceptors.response.use(
                    (resp) => {
                        //interceptor task goes here like attaching a jwt token to a req ..etc.,
                        return resp;
                    },
                    (error) => {
                        //global error handling may go here...
                    }
                );

    Promise, async and await ?
    ------------------------------------------------
        Promise is a class that provides communication
        between an asynchronous background operation and
        a synchronous front-end.
        
        Every async method returns a promise object that allows to handle the return value or error.

        const method1 = () => MAth.PI
        const method2 = (a,b) => a+b

        x = method1()
        console.log(x);
        y = method2()   //method2 will not start execution until method1 is complete
        console.log(y);
        
        
        const asynchronousMethod1 = async () => {
            //here it must be a time consuming operation 
            return Math.PI;
        }

        const method2 = (a,b) => a+b

        p = asynchronousMethod1()   //here p is a promise
        p.then( x => console.log(x); )
            .catch( err => console.log(err); );

        y = method2()               //method2 will not wait until asynchronousMethod1 is complete, 
                                    but executes parellally
        console.log(y);

        const dummy =async () => {
            x = await asynchronousMethod1()   
            console.log(x);
            y = method2()               //method2 will wait until asynchronousMethod1 is complete due to 'await', 
                                        but executes parellally
            console.log(y);
        }

        dummy();

        Note: 'await' keyword can be used only inside the body of another async method.

    Thunk - to integrate 'rest-api' calls with redux.
    -------------------------------------------------------
        
        'createAsyncThunk' from redux-tool-kit will create
        special actions called thunk-actions. 

        Each 'thunk-action or otherwise called async-thunk' is essentially a
        async function that takes up a async job like api-calls and
        generates three events "pending","fulfilled" and "rejected".

        'slices' can listen (using extra-reducers) to these events and update their "store" accordingly.

            store -------------------------------------------
                ↑               ↓                           ↓
                |               |                           |
                |               Component1                  |
                |               |                           Component2
                |               | dispatch(action)          |
                |               |                           | dispatch(thunkAction)
             |->|               |                           ↓
             |   reducer(s) ←---|                    ----[async-thunk-action]--------
            extra-reducers(s)                        |                              |   
                        ↑                            |                              |
                        |←---------------------------|  raise-pending-evemnt        |
                        |                            |  axios-call                  |------> rest-api <---> database
                        |                            |                              |           ↓
                        |←---------------------------|  raise-fulfilled-event       |<---data---|
                        |←---------------------------|  raise-rejected-event        |<---error--|
                                                     |------------------------------|

            Note: more than one slice can listen to any of the three events of a async-thunk-action

        Example

            export const fetchUsers = createAsyncThunk('users/fetchUsers', async () => {
                const response = await axios.get('/api/users');
                return (response.data) as User[];
            });

            const usersAdapter = createEntityAdapter<User>();
            /*
                default userSliceState will be
                {
                    ids: [],
                    users:{}
                }
            */

            interface UsersSliceExtaState{
                status:'idle' | 'loading' | 'succeeded' | 'failed';
                error:string|null;
            } 

            /*
                default userSliceState will be attached with the extra state like 
                {
                    ids: [],
                    users:{},
                    status: 'idle',
                    error:null
                }
            */
            
            const usersSlice = createSlice({
                name: 'users',
                initialState: usersAdapter.getInitialState<UsersSliceExtaState>({
                    status: 'idle', // Custom metadata state
                    error:null
                }),
                reducers: {},
                // 3. Manually map the thunk to the adapter's auto-generated mutation logic
                extraReducers: (builder) => {
                    builder
                        .addCase(fetchUsers.pending, (state) => {
                            state.status = 'loading';
                        })
                        .addCase(fetchUsers.fulfilled, (state, action) => {
                            state.status = 'succeeded';                            
                            // 🔥 This passes the action.payload right into the adapter.
                            // It clears out old data and populates ids and entities in 1 line.
                            usersAdapter.setAll(state, action.payload); 
                        })
                        .addCase(fetchUsers.rejected, (state,action) => {
                            state.status = 'failed';
                            state.error = "unable to load data";
                            console.error(action.payload); //err is contained in the payload
                        });
                },
            });

            export default usersSlice.reducer;

    Create-fake-rest-api using Json-Server
    ------------------------------------------------

        md adb-api
        cd adb-api
        npm init -y
        npm i json-server@0.17.4

        create a json file 'adb-api/data.json' that contians the hypothetical data

        create script "start":"json-server --port 9999 --watch ./data.json" in package.json

    Memorizaion
    ------------------------------------------------
        In React, memoization is all about performance optimization. At its core, it’s a strategy to avoid doing the same work twice. By "remembering" the results of expensive calculations or preventing unnecessary component re-renders, the application is kept snappy.

        1. React.memo: Component Memoization
            By default, when a parent component re-renders, all of its children re-render too—even if their props haven't changed. React.memo is a Higher Order Component (HOC) that prevents this. React performs a "shallow comparison" of the props. 
            If the props are the same as last time, React skips rendering the component and reuses the last rendered result.

        2. useMemo: Value Memoization
            useMemo is a Hook that lets us cache the result of a calculation between re-renders.

        3. useCallback: Function Memoization
            useCallback is a Hook that lets you cache a function definition itself between re-renders.

            In JavaScript, function(){} === function(){} is false. Every time a component re-renders, any function defined inside it is a "new" function. This causes child components wrapped in React.memo to re-render anyway because their "prop" (the function) looks different.

            The Solution is useCallback which ensures that the function reference stays the same unless its dependencies change.

        For Example 

            Unoptimized Version

                const ItemList = ({ items, onItemClick }) => {                 
                    console.log("ItemList Rendered"); // logs whenever ProductPage is re-rendered
                    return (
                        <ul>
                         {items.map(i => <li key={i.id} onClick={onItemClick}>{i.name}</li>)}
                        </ul>
                    );
                };

                const ProductPage = ({ items, theme }) => {
                    const [count, setCount] = useState(0);

                    // 1. Expensive calculation runs on EVERY click of "Increment"
                    const visibleItems = items.filter(item => item.price < 100);

                    // 2. This function is "new" on every render, breaking child memoization
                    const addToCart = () => {
                        console.log("Added!");
                    };

                    return (
                        <div className={theme}>
                            <h1>Count: {count}</h1>
                            <button onClick={() => setCount(count + 1)}>Increment</button>
                            
                            <ItemList items={visibleItems} onItemClick={addToCart} />
                        </div>
                    );
                }

            Optimized Version

                // 1. Wrap the child in React.memo
                const ItemList = React.memo(({ items, onItemClick }) => {
                    // Only logs when items or onItemClick change
                    console.log("ItemList Rendered"); 

                    return (
                        <ul>
                        {items.map(i => <li key={i.id} onClick={onItemClick}>{i.name}</li>)}
                        </ul>
                    );
                });

                const ProductPage = ({ items, theme }) => {
                    const [count, setCount] = useState(0);

                    // 2. Memoize the filtered list
                    const visibleItems = useMemo(() => {
                        return items.filter(item => item.price < 100);
                    }, [items]); // Only re-runs if 'items' prop changes

                    // 3. Memoize the function reference
                    const addToCart = useCallback(() => {
                        console.log("Added!");
                    }, []); // Reference stays the same forever

                    return (
                        <div className={theme}>
                            <h1>Count: {count}</h1>
                            <button onClick={() => setCount(count + 1)}>Increment</button>
                            
                            <ItemList items={visibleItems} onItemClick={addToCart} />
                        </div>
                    );
                }

    BudgetTrackingApp
    ------------------------------------------------

    Customer
        |<-multiple-> Accounts
                        |<-multiple-> Transactions

    Customer        id (CRIN), Name, Mobile, MailId
    Account         id (AccNum), Type (Savings|Current), CurrentBalance
    Transaction     id (TxnId), TxnDate, Header, Amount, TxnType
    
    sample json 
        {
            "customer":[
                {"id":1,"name":"Vamsy","mobile":"9052224753","mailId":"vamsy@gmail.com"}
            ],
            "accounts":[
                {"id":1,"type":"SAVINGS",currentBalance:0,"crin":1}
            ],
            "txns":[
                
            ]
        }
    Links on the navbar
        /home   that brigns up the customers page.

        Custoemr Page is the landing page
            1. it has to support CRUD operations on custoemrs
            2. it must have a nested table that manages CRUD operations of Accounts
                linked to a specific customer.
            3. Accounts nested table must appear only when the related customer row is clicked
                like an ACCORDIAN
            4. Against each account record, apart from edit and delete buttons, a statement button is needed 
                that when clicked will navigate to statement page
            5. Use a bootstrap model to display custoemr-form or account-form for
                add or edit operations

        Statement page
            1. is the page that supports CRUD operatiosn on transactions
            2. Any add/update/delete operation on transaction must
                trigger an update on the currentBalance of the related account.

Handle a sequence of api calls - RTK - thunk
-------------------------------------------------------

    "Where is the brain?"

        Are we handling the bussiness logic on the UX-app or the rest-api-server?

        in case - BL - (on adding or deleting or updating a txn, currentBalence update) is handled
        by the api-server =====> then we jsut need to update the state on RTK for both 
        txn-slcie and accounts-slice.

        in our case, we depend on "json-server" for rest-api which is absolutly dumb and handles NO-BL,
        brain is at the UX-app

        so, in the addTxnAction

            const txnApiResp = await axios.post(txnApi,txn);

            //compute the currentBalacne

            const accApiResp = await axios.patch(accApi + "/" + txn.accId,{currentBal:cb});

            // return both the txn (to be used by txn-slice) and {accid,currentBal} (to be used by acc-slice)

        Now to update the 'state' on RTK

            we can add extrReducer on addTxnAction.fullfilled   in the txns-sclice   to push txn into state.txns
            we can also add extrReducer on addTxnAction.fullfilled   in the acc-sclice   to change the state.accs[index].currentBal