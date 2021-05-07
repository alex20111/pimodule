import { Icons } from './../_enums/icons';

// get the string name from the Icons to fetch the file
export function getIconsFromString(iconStringName: string): Icons {
    let returnedIcon: Icons;

    // tslint:disable-next-line: forin
    for (const icon in Icons) {

        const ico = Icons[icon] as string;

        if (iconStringName.toUpperCase() === ico.toUpperCase()) {
            returnedIcon = Icons[icon];
            break;
        }
        // console.log("values--> ", iconStringName, icon, ico, returnedIcon);

    }

    if (returnedIcon == null){
        returnedIcon = Icons.NA;
    }

    return returnedIcon;
}