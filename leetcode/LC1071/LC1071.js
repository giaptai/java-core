function gcdOfStrings(str1, str2){
    if (!(str1 + str2 === str2 + str1)){
        return "";
    }
    const lenSub = gdc(str1.length, str2.length)
    return str2.substring(0, lenSub);
}

function gdc(l1, l2){
    // if l2 is 0 l1 is a greatest common divisor
    // if not then continue module operation
    return l2 === 0 ? l1 : gdc(l2, l1 % l2);
}