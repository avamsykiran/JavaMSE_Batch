import { yupResolver } from "@hookform/resolvers/yup";
import { Alert, Button, Card, CardBody, CardFooter, CardHeader, Col, Form, FormControl, FormGroup, FormLabel, FormText } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";
import * as yup from "yup";
import { selectAuthErrMsg, selectAuthIsLoading, selectIsAuthenticated } from "../lib/reduxState/selectors";
import { register as registerUser } from '../lib/reduxState/userThunks';

type RegData = { username: string, password: string, confirmPassword: string };

function Registration() {
    const isAuthenticated = useSelector(selectIsAuthenticated);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const isLoading = useSelector(selectAuthIsLoading);
    const errMsg = useSelector(selectAuthErrMsg);

    if (isAuthenticated) {
        navigate("/");
    }

    const regSchema: yup.ObjectSchema<RegData> = yup.object({
        username: yup.string()
            .required('UserName is a mandatory field')
            .min(5, "Expecting a minimum length of 5 chars")
            .max(25, "Expecting a maximum length of 25 chars"),
        password: yup.string()
            .required('Password is a mandatory field')
            .min(5, "Expecting a minimum length of 5 chars")
            .max(25, "Expecting a maximum length of 25 chars"),
        confirmPassword: yup.string()
            .required('Confirm Password is a mandatory field')
            .oneOf([yup.ref('password')], 'Passwords must match'),
    });

    const {
        register,
        handleSubmit,
        formState: { errors, isValid },
    } = useForm<RegData>({
        resolver: yupResolver(regSchema),
        mode: "onTouched",
        defaultValues: {
            username: "",
            password: "",
            confirmPassword: ""
        }
    });

    const doRegister = (data: RegData) => {
        dispatch(registerUser({username:data.username,password:data.password}));
        navigate("/login");        
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

            <Form onSubmit={handleSubmit(doRegister)}>
                <Card bg="info">
                    <CardHeader>
                        Sign Up
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
                        <FormGroup className="mb-2" controlId="pwd">
                            <FormLabel>Confirm Password</FormLabel>
                            <FormControl type="password" {...register("confirmPassword")} />
                            {errors.confirmPassword && (
                                <FormText className="text-danger">
                                    {errors.confirmPassword.message}
                                </FormText>
                            )}
                        </FormGroup>
                    </CardBody>
                    <CardFooter className="text-end">
                        <Button variant="primary" disabled={!isValid} type="submit">
                            Sign Up
                        </Button>
                    </CardFooter>
                </Card>
            </Form>
        </Col>
    )
}

export default Registration;