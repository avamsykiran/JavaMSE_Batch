import { Alert, Button, Card, CardBody, CardHeader, Table } from "react-bootstrap";
import { Link } from "react-router";
import { type DeleteContactData, type DeleteContactVars, type GetAllContactsData } from "../lib/graphql/contactGQLTypes";
import { DELETE_CONTACT, GET_ALL_CONTACTS } from "../lib/graphql/contactsOperations";
import { useMutation, useQuery } from "@apollo/client/react";

function ContactsList() {

    const { data, loading, error } = useQuery<GetAllContactsData>(GET_ALL_CONTACTS);

    const [removeContact] = useMutation<DeleteContactData,DeleteContactVars>(DELETE_CONTACT,{
        refetchQueries: [{ query: GET_ALL_CONTACTS }],
    });

    return (
        <Card>
            <CardHeader>Contacts List</CardHeader>
            <CardBody>

                {loading && (
                    <Alert variant="info">
                        <p>Please wait while loading</p>
                    </Alert>
                )}
                
                {error && (
                    <Alert variant="danger">
                        <p>{error.message}</p>
                    </Alert>
                )}

                {
                    data && data.allContacts.length > 0 ? (
                        <Table striped hover bordered>
                            <thead>
                                <th>Contact#</th>
                                <th>Name</th>
                                <th>Mobile</th>
                                <th>Mail</th>
                                <th>DoB</th>
                                <th>Actions</th>
                            </thead>
                            <tbody>
                                {data.allContacts.map(cx => (
                                    <tr key={cx.contactId}>
                                        <td>{cx.contactId}</td>
                                        <td>{cx.fullName}</td>
                                        <td>{cx.mobileNumber}</td>
                                        <td>{cx.mailId}</td>
                                        <td>{cx.dateOfBirth}</td>
                                        <td>
                                            <Link to={`/edit/${cx.contactId}`}
                                                className="btn btn-sm btn-info me-1">
                                                <i className="bi-pen" /> EDIT
                                            </Link>
                                            <Button variant="danger" size="sm"
                                                onClick={_e => removeContact({variables:{contactId:cx.contactId}})}>
                                                <i className="bi-trash" /> DELETE
                                            </Button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    ) : (
                        <Alert variant="info">
                            <p>No Data Found</p>
                        </Alert>
                    )
                }

            </CardBody>
        </Card>
    )
}

export default ContactsList;