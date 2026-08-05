import { gql } from '@apollo/client';

// Shared Field Selection Fragment
export const CONTACT_FIELDS = gql`
  fragment ContactFields on Contact {
    contactId
    fullName
    mobileNumber
    mailId
    dateOfBirth
  }
`;

// Queries
export const GET_ALL_CONTACTS = gql`
  query GetAllContacts {
    allContacts {
      ...ContactFields
    }
  }
  ${CONTACT_FIELDS}
`;

export const GET_CONTACT_BY_ID = gql`
  query GetContactById($contactId: ID!) {
    contactById(contactId: $contactId) {
      ...ContactFields
    }
  }
  ${CONTACT_FIELDS}
`;

// Mutations
export const CREATE_CONTACT = gql`
  mutation CreateContact($input: CreateContactInput!) {
    createContact(input: $input) {
      ...ContactFields
    }
  }
  ${CONTACT_FIELDS}
`;

export const UPDATE_CONTACT = gql`
  mutation UpdateContact($contactId: ID!, $input: UpdateContactInput!) {
    updateContact(contactId: $contactId, input: $input) {
      ...ContactFields
    }
  }
  ${CONTACT_FIELDS}
`;

export const DELETE_CONTACT = gql`
  mutation DeleteContact($contactId: ID!) {
    deleteContact(contactId: $contactId)
  }
`;