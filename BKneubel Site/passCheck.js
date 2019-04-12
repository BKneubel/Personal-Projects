
function CheckPassword(inputtxt)
{
    var passw = /Mp214712013/;
    var bool = false;
    if(inputtxt.value.match(passw))
    {
        alert('hi');
        bool = true;
        return true;
    }
    else
    {
        alert('Wrong...!');
        bool = false;
        return false;
    }

}

