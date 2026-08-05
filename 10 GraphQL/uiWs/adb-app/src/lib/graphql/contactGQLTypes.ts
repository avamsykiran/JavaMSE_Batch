import type { Contact } from "../models/Contact";

export interface CreateContactInput {
  fullName: string;
  mobileNumber: string;
  mailId: string;
  dateOfBirth: string;
}

export interface UpdateContactInput {
  fullName?: string;
  mobileNumber?: string;
  mailId?: string;
  dateOfBirth?: string;
}

// Operation Data & Variable Types
export interface GetAllContactsData {
  allContacts: Contact[];
}

export interface GetContactByIdData {
  contactById: Contact | null;
}

export interface GetContactByIdVars {
  contactId: number;
}

export interface CreateContactData {
  createContact: Contact;
}

export interface CreateContactVars {
  input: CreateContactInput;
}

export interface UpdateContactData {
  updateContact: Contact;
}

export interface UpdateContactVars {
  contactId: string;
  input: UpdateContactInput;
}

export interface DeleteContactData {
  deleteContact: boolean;
}

export interface DeleteContactVars {
  contactId: number;
}