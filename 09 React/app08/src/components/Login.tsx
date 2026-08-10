import { yupResolver } from "@hookform/resolvers/yup";
import { Alert, Button, Card, CardBody, CardFooter, CardHeader, Col, Form, FormControl, FormGroup, FormLabel, FormText } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import * as yup from "yup";
import { login } from "../lib/reduxState/userThunks";
import { useNavigate } from "react-router";
import { selectAuthErrMsg, selectAuthIsLoading, selectIsAuthenticated } from "../lib/reduxState/selectors";

type LoginData = { username: string, password: string };

function Login() {

    const isAuthenticated = useSelector(selectIsAuthenticated);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const isLoading = useSelector(selectAuthIsLoading);
    const errMsg = useSelector(selectAuthErrMsg);

    if (isAuthenticated) {
        navigate("/");
    }

    const loginSchema: yup.ObjectSchema<LoginData> = yup.object({
        username: yup.string()
            .required('UserName is a mandatory field')
            .min(5, "Expecting a minimum length of 5 chars")
            .max(25, "Expecting a maximum length of 25 chars"),
        password: yup.string()
            .required('Password is a mandatory field')
            .min(5, "Expecting a minimum length of 5 chars")
            .max(25, "Expecting a maximum length of 25 chars"),

    });

    const {
        register,
        handleSubmit,
        formState: { errors, isValid },
    } = useForm<LoginData>({
        resolver: yupResolver(loginSchema),
        mode: "onTouched",
        defaultValues: {
            username: "",
            password: ""
        }
    });

    const doLogin = (ld: LoginData) => {
        dispatch(login(ld));        
    }

    return (
        <Col sm={5} xs className="mx-auto">

            {
                isLoading && (
                    <Alert variant="info" className="m-2 p-2">
                        <strong>Please wait while processing your request..! </strong>
                    </Alert>
                )
            }

            {
                errMsg && (
                    <Alert variant="danger" className="m-2 p-2">
                        <strong>{errMsg} </strong>
                    </Alert>
                )
            }

            <Form onSubmit={handleSubmit(doLogin)}>
                <Card bg="info">
                    <CardHeader>
                        Sign In
                    </CardHeader>
                    <CardBody>
                        <FormGroup className="mb-2" controlId="unm">
                            <FormLabel>User Name</FormLabel>
                            <FormControl type="text" {...register("username")} />
                            {errors.username && (
                                <FormText className="text-danger">
                                    {errors.username.message}
                                </FormText>
                            )}
                        </FormGroup>
                        <FormGroup className="mb-2" controlId="pwd">
                            <FormLabel>Password</FormLabel>
                            <FormControl type="password" {...register("password")} />
                            {errors.password && (
                                <FormText className="text-danger">
                                    {errors.password.message}
                                </FormText>
                            )}
                        </FormGroup>
                        
                    </CardBody>
                    <CardFooter className="text-end">
                        <Button variant="primary" disabled={!isValid} type="submit">
                            Sign In
                        </Button>
                    </CardFooter>
                </Card>
            </Form>
        </Col>
    )
}

export default Login;