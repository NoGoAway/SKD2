function EAS_load_fif(div, fifSrc, easSrc, width, height) {
   var d = document,
       fif = d.createElement("iframe");
//       div = d.getElementById(divId);

   fif.src = fifSrc;
   fif.style.width = width + "px";
   fif.style.height = height + "px";
   fif.style.margin = "0px";
   fif.style.borderWidth = "0px";
   fif.style.padding = "0px";
   fif.style.display = 'block';
   fif.scrolling = "no";
   fif.frameBorder = "0";
   fif.allowTransparency = "true";
   fif.EAS_src = easSrc + ";fif=y";
   $(div).html(fif);
}
