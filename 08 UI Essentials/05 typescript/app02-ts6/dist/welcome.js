const greet = function (userName, salutation) {
    return "Hello " + (salutation ? salutation : "") + userName;
};
export default greet;
