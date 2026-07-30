import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardFooter, CardHeader, Col, Form, FormControl, FormGroup, FormLabel, FormText, Row } from "react-bootstrap";
import { useLocation, useNavigate, useParams } from "react-router";
import * as yup from "yup";
import type { Contact } from "../lib/models/Contact";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { useDispatch, useSelector } from "react-redux";
import { selectContactById, selectContactsCount } from "../lib/state/selectors";
import type { AppDispatch, RootState } from "../lib/state/appStore";
import { addContact, updateContact } from "../lib/state/contactsSlice";

function ContactForm() {

    const contactSchema: yup.ObjectSchema<Contact> = yup.object({
        contactId: yup.number()
            .required('Conmtact Id is a mandatory field'),
        fullName: yup.string()
            .required('Full Name is a mandatory field')
            .min(5, "Expecting a minimum length of 5 chars")
            .max(25, "Expecting a maximum length of 25 chars"),
        mobileNumber: yup.string()
            .required('Mobile Number is a mandatory field')
            .matches(/^[1-9][0-9]{9}$/, 'Mobile Number must be of 10 digits'),
        mailId: yup.string()
            .required('Mail Id is a mandatory field')
            .email("A Valid email id expected"),
        dateOfBirth: yup.string()
            .required('DoB is a mandatory field')
    });

    const {
        register,
        handleSubmit,
        reset,
        formState: { errors, isValid },
    } = useForm<Contact>({
        resolver: yupResolver(contactSchema),
        mode: "onTouched",
        defaultValues: {
            contactId: 0,
            fullName: "",
            mobileNumber: "",
            mailId: "",
            dateOfBirth: ""
        }
    });

    const { pathname } = useLocation();
    const { id } = useParams();

    const oldContact:Contact = useSelector((state:RootState) => selectContactById(state,Number(id)));
    const count:number = useSelector(selectContactsCount);
    const dispatch:AppDispatch = useDispatch();
    const navigate = useNavigate();

    const [isNew, setNew] = useState<boolean>(true);

    useEffect(() => {
        if (pathname.startsWith("/edit") && id && oldContact) {
            setNew(false);    
            reset(oldContact);
        } else {
            setNew(true);
        }
    }, [pathname,id,oldContact])

    const save = (c:Contact) => {
        if(isNew){
            dispatch(addContact({...c,contactId:count+1}))
        }else{
            dispatch(updateContact({changes:c,id:c.contactId}) )
        }
        navigate("/list");
    }

    return (
        <Col sm={5} xs className="mx-auto">
            <Form onSubmit={handleSubmit(save)}>
                <Card bg="info">
                    <CardHeader>
                        {isNew ? "New" : "Edit"} Contact
                    </CardHeader>
                    <CardBody>
                        <FormGroup className="mb-2" controlId="cid">
                            <FormLabel>Contact Id</FormLabel>
                            <FormControl type="number" readOnly {...register("contactId")} />
                            { errors.contactId && (
                                <FormText className="text-danger">
                                    {errors.contactId.message}
                                </FormText>
                            ) }
                        </FormGroup>
                        <FormGroup className="mb-2" controlId="fnm">
                            <FormLabel>Full Name</FormLabel>
                            <FormControl type="text" {...register("fullName")} />
                            { errors.fullName && (
                                <FormText className="text-danger">
                                    {errors.fullName.message}
                                </FormText>
                            ) }
                        </FormGroup>
                        <FormGroup className="mb-2" controlId="mno">
                            <FormLabel>Mobile Number</FormLabel>
                            <FormControl type="number" {...register("mobileNumber")} />
                            { errors.mobileNumber && (
                                <FormText className="text-danger">
                                    {errors.mobileNumber.message}
                                </FormText>
                            ) }
                        </FormGroup>
                        <FormGroup className="mb-2" controlId="mid">
                            <FormLabel>Mail Id</FormLabel>
                            <FormControl type="email" {...register("mailId")} />
                            { errors.mailId && (
                                <FormText className="text-danger">
                                    {errors.mailId.message}
                                </FormText>
                            ) }
                        </FormGroup>
                        <FormGroup className="mb-2" controlId="dob">
                            <FormLabel>Date of Birth</FormLabel>
                            <FormControl type="date" {...register("dateOfBirth")} />
                            { errors.dateOfBirth && (
                                <FormText className="text-danger">
                                    {errors.dateOfBirth.message}
                                </FormText>
                            ) }
                        </FormGroup>
                    </CardBody>
                    <CardFooter className="text-end">
                        <Button variant="primary" disabled={!isValid} type="submit"> 
                            <i className="bi-floppy" /> SAVE 
                        </Button>
                    </CardFooter>
                </Card>
            </Form>
        </Col>
    )
}

export default ContactForm;