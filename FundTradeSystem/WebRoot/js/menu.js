var bx;
var cl = new Array();
var bQ = false;
var ck = [];
var cj = [];
var co = [];
var bE = [];
var cm = [];
var cE = [];
var bQ = bP = cy = cx = cB = ff = ct = bS = bT = cd = bR = cs = cv = cc = cw = false;
var bZ = 0;
var cn = null;
var cg = null;
var bn = -1;
var cf = true;
var bJ = false;
var bp = 0;
var bw = -1;
var bI = 0, cL = 0;
var cu = false;
var bo = false;
var ci = null, bu = 0;
var z = 999;
var bA = false;
var bG = [];
var hBorderCount = 0;
var _ = null;
cf = true;
O();
cf = false;
function globalCSS() {
	var gN = '<STYLE>.umtable {BACKGROUND-IMAGE: none; WIDTH: auto; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: auto; BACKGROUND-COLOR: transparent; BORDER-BOTTOM-STYLE: none;position:static}';
	gN += '.umtr {BACKGROUND-IMAGE: none; WIDTH: auto; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: auto; BACKGROUND-COLOR: transparent; BORDER-BOTTOM-STYLE: none}';
	gN += '.umtd {BACKGROUND-IMAGE: none; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: auto; BACKGROUND-COLOR: transparent; BORDER-BOTTOM-STYLE: none}';
	gN += '.umdiv {PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 1px; BACKGROUND-IMAGE: none; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: auto; PADDING-TOP: 0px; HEIGHT: auto; BACKGROUND-COLOR: transparent}';
	gN += '.uma {	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; BACKGROUND-IMAGE: none; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: auto; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: auto; BACKGROUND-COLOR: transparent; BORDER-BOTTOM-STYLE: none}';
	gN += '</STYLE>';
	document.write(gN);
};
globalCSS();
function SysRemove(he) {
	bA = true;
	var gU = bw;
	bw = 0;
	for ( var i = 0; i <= he.length - 1; i++) {
		var m = al(he[i]);
		var db = aq(m);
		db = I(null, db);
		db.length = db.length;
		for ( var j = db.length - 1; j >= 0; j--) {
			if (!document.getElementById(db[j].gr)) {
				var gV = N(db[j], 0);
			} else {
				var gV = document.getElementById(db[j].gr);
			}
			if (!db[j].aD) {
				var gn = document.getElementById(db[j].gb.gr);
				gV.style.visibility = 'visible';
				aQ(gV, gn);
				cE[cE.length] = db[j];
			}
		}
		ax(document.getElementById(m.gr));
	}
	bw = gU;
};
function HaltDown() {
	bA = false;
	av();
	aJ();
};
function ak() {
	if (++hBorderCount > bG.length) {
		opi = document.createElement('DIV');
		var b = document.getElementsByTagName('BODY')[0];
		with (opi.style) {
			visiblity = 'hidden';
			position = 'absolute';
			zIndex = '99999';
			border = "2px solid red";
			fontSize = '0';
		}
		b.appendChild(opi);
		bG[bG.length] = opi;
		return opi;
	} else {
		return bG[hBorderCount];
	}
};
function aJ() {
	var b = document.getElementsByTagName('BODY')[0];
	for ( var i = 0; i <= bG.length - 1; i++) {
		if (bG[i].style.visibility != 'hidden') {
			bG[i].style.visibility = 'hidden';
		}
	}
};
function ax(gn) {
	if (aD(gn)) {
		var dh = document.getElementsByTagName('BODY')[0].offsetTop;
		var dg = document.getElementsByTagName('BODY')[0].offsetLeft;
	} else {
		var dh = 0;
		var dg = 0;
	}
	var d = ak();
	d.style.left = S(gn) + dg - 2;
	d.style.top = T(gn) + dh - 2;
	d.style.width = gn.offsetWidth + 4;
	d.style.height = gn.offsetHeight + 4;
};
function aq(eY) {
	if (!eY)
		return;
	log('current item is ' + eY.dE);
	var result = [];
	if (eY.di) {
		result[0] = eY.di;
	}
	result[1] = eY.ga;
	var t = eY.gb;
	while (t) {
		result[result.length] = t.ga;
		t = t.gb;
	}
	for ( var i = 0; i <= result.length - 1; i++) {
		if (result[i])
			log('need show groups' + result[i].id);
	}
	return result;
};
function HideDiv() {
	K();
	aA();
};
function aU(filter) {
	gO = filter.split('none(');
	if (gO.length > 1) {
		gO[0] += 'progid:DXImageTransform.Microsoft.AlphaImageLoader';
		return gO[0] + '(enabled=false,' + gO[1];
	} else {
		return filter;
	}
};
function BuildLayer(bz, move, scroll, bo, cF, bH, x, y, bt, padding, cI,
		borderLeft, borderRight, borderTop, borderBottom, bF, bgColor, G, aX,
		ba, bv, filter, cD, shadowColor, cC, cursor, by, cM, k, hc, gv) {
	var eS = {
		aD : true,
		dM : bz,
		dY : move,
		er : scroll,
		dH : bo,
		es : cF,
		dQ : bH,
		fx : x,
		fy : y,
		dI : bt,
		dG : cI,
		dF : padding,
		dJ : bv,
		dN : aU(filter),
		eb : borderLeft,
		ec : borderRight,
		ed : borderTop,
		ea : borderBottom,
		dA : bgColor,
		dB : G,
		dC : aX,
		dD : ba,
		dO : bF,
		eq : cD,
		eo : shadowColor,
		ep : cC,
		dK : cursor,
		dL : by,
		eu : cM,
		dw : k,
		eW : 0,
		eV : 0,
		cW : (gv == '#FFFFFF') ? true : false
	};
	if (bP && (by.toLowerCase() == 'pointer')) {
		eS.dL = 'hand';
	}
	if (bP && (cursor.toLowerCase() == 'pointer')) {
		eS.dK = 'hand';
	}
	cj[cj.length] = co[0] = eS;
	cm[cm.length] = cj.length - 1;
};
function aF(cZ) {
	if (cZ == null) {
		return '';
	} else {
		return cZ;
	}
};
function SwapDiv(ca, cK, bm, bM, bN, bL, bO, v, B, r, C, height, width, bB, bC,
		cG, cH, cN, cO, bq, bs, cS, cT, cP, cQ, D, F, Q, aW, bb, bc, aY, aZ,
		bf, bh, bj, bd, bg, bi, bk, be, j, g, cp, cR, status, target, cz, cq,
		cb, bW, bX, bV, bY, n, bK, bU) {
	var cY = new Object();
	cY.eg = new Object();
	var gJ = null;
	if (cK != '') {
		gJ = al(cK);
	}
	cY = {
		id : ca,
		eK : ck.length,
		gB : cm[cm.length - 1],
		dE : bm == null && gJ ? gJ.dE : bm,
		eg : {
			fu : (bB == null && gJ) ? gJ.eg.fu : bB,
			fv : (cG == null && gJ) ? gJ.eg.fv : cG,
			fw : (cN == null && gJ) ? gJ.eg.fw : cN,
			ft : (bq == null && gJ) ? gJ.eg.ft : bq,
			fA : cS == null && gJ ? gJ.eg.fA : cS,
			fz : cP == null && gJ ? gJ.eg.fz : cP,
			fk : (D == null && gJ) ? gJ.eg.fk : D,
			fl : (Q == null && gJ) ? gJ.eg.fl : Q,
			fn : (bb == null && gJ) ? gJ.eg.fn : bb,
			fm : (aY == null && gJ) ? gJ.eg.fm : aY,
			fp : (bf == null && gJ) ? gJ.eg.fp : bf,
			fq : (bh == null && gJ) ? gJ.eg.fq : bh,
			fr : (bj == null && gJ) ? gJ.eg.fr : bj,
			fo : (bd == null && gJ) ? gJ.eg.fo : bd
		},
		ek : {
			fO : (bC == null && gJ) ? gJ.ek.fO : bC,
			fQ : (cH == null && gJ) ? gJ.ek.fQ : cH,
			fR : (cO == null && gJ) ? gJ.ek.fR : cO,
			fN : (bs == null && gJ) ? gJ.ek.fN : bs,
			fU : cT == null && gJ ? gJ.ek.fU : cT,
			fS : cQ == null && gJ ? gJ.ek.fS : cQ,
			fF : (F == null && gJ) ? gJ.ek.fF : F,
			fG : (aW == null && gJ) ? gJ.ek.fG : aW,
			fI : (bc == null && gJ) ? gJ.ek.fI : bc,
			fH : (aZ == null && gJ) ? gJ.ek.fH : aZ,
			fK : (bg == null && gJ) ? gJ.ek.fK : bg,
			fL : (bi == null && gJ) ? gJ.ek.fL : bi,
			fM : (bk == null && gJ) ? gJ.ek.fM : bk,
			fJ : (be == null && gJ) ? gJ.ek.fJ : be
		},
		dv : (j == null && gJ) ? gJ.dv : j,
		du : (g == null && gJ) ? gJ.du : g,
		ez : (cp == null && gJ) ? gJ.ez : cp,
		ey : (cR == null && gJ) ? gJ.ey : cR,
		et : (status == null && gJ) ? gJ.et : status,
		ex : (target == null && gJ) ? gJ.ex : target,
		dZ : (v == null && gJ) ? gJ.dZ : v,
		eh : (B == null && gJ) ? gJ.eh : B,
		dy : (r == null && gJ) ? gJ.dy : r,
		dz : (C == null && gJ) ? gJ.dz : C,
		dx : (n == null && gJ) ? gJ.dx : n,
		ee : (bM == null && gJ) ? gJ.ee : bM,
		ei : (bN == null && gJ) ? gJ.ei : bN,
		dS : (bL == null && gJ) ? gJ.dS : bL,
		dT : (bO == null && gJ) ? gJ.dT : bO,
		dR : (bK == null && gJ) ? gJ.dR : bK,
		dP : (height == null && gJ) ? gJ.dP : height,
		eA : (width == null && gJ) ? gJ.eA : width,
		dW : (bY == null && gJ) ? gJ.dW : bY,
		dV : (bV == null && gJ) ? gJ.dV : bV,
		ef : (bW == null && gJ) ? gJ.ef : bW,
		ej : (bX == null && gJ) ? gJ.ej : bX,
		dU : (bU == null && gJ) ? gJ.dU : bU,
		ew : cz,
		ev : cq,
		dX : (cb == null && gJ) ? gJ.dX : cb * 1,
		gb : co[co.length - 1].gb,
		ga : co[co.length - 1],
		gr : null,
		gq : null,
		di : null,
		gk : null,
		gh : null
	};
	if (!isNaN(bO) && (cb * 1 != 2)) {
		cY.ga.eW = (cY.dT * 1 > cY.ga.eW * 1) ? cY.dT : cY.ga.eW;
	}
	if (!isNaN(C) && (cb * 1 != 2)) {
		cY.ga.eV = (cY.dz * 1 > cY.ga.eV * 1) ? cY.dz : cY.ga.eV;
	}
	ck[ck.length] = cY;
};
function aO(gp) {
	var fh = gp.fb;
	if (!fh)
		return;
	var G = fh.eg.fl;
	if ((bP) && (G.indexOf('blank.gif') != -1)) {
		G = '';
	}
	with (gp.style)
		with (fh.eg) {
			{
				backgroundColor = fk;
				if (fl != '') {
					backgroundImage = 'url(' + G + ')';
					backgroundRepeat = 'no-repeat';
					if (fn == true) {
						backgroundRepeat = 'repeat-y'
					} else if (fm == true) {
						backgroundRepeat = 'repeat-x';
					}
					if ((fn == true) && (fm == true)) {
						backgroundRepeat = 'repeat';
					}
				} else {
					backgroundImage = '';
				}
				if (borderLeft != fp)
					borderLeft = fp;
				if (borderRight != fq)
					borderRight = fq;
				if (borderTop != fr)
					borderTop = fr;
				if (borderBottom != fo)
					borderBottom = fo;
			}
		}
	var eJ = document.getElementById(fh.gk);
	var de = document.getElementById(fh.gh);
	var text = document.getElementById(fh.gu);
	var img = document.getElementById(fh.gm);
	if (img) {
		img.src = fh.ef;
	}
	if ((text)) {
		with (text.style) {
			with (fh.eg) {
				if (fontFamily != fu)
					fontFamily = fu;
				if (fontSize != fv)
					fontSize = fv;
				if (fontWeight != fA)
					fontWeight = fA;
				if (fontStyle != fw)
					fontStyle = fw;
				if (textDecoration != fz)
					textDecoration = fz;
				if (color != ft)
					color = ft;
			}
		}
	}
	if ((fh.ee) && (eJ)) {
		if (eJ.src != fh.ee)
			eJ.src = fh.ee;
	}
	if ((fh.dZ) && (de)) {
		if (de.src != fh.dZ)
			de.src = fh.dZ;
	}
	if (window.status == fh.et) {
		window.status = '';
	}
};
function o(gp, eY) {
	var fh = eY;
	var G = fh.ek.fG;
	if ((bP) && (G.indexOf('blank.gif') != -1)) {
		G = '';
	}
	with (gp.style)
		with (fh.ek) {
			{
				backgroundColor = fF;
				backgroundImage = 'url(' + G + ')';
				backgroundRepeat = 'no-repeat';
				if (fI == true) {
					backgroundRepeat = 'repeat-y'
				} else if (fH == true) {
					backgroundRepeat = 'repeat-x';
				}
				if ((fI == true) && (fH == true)) {
					backgroundRepeat = 'repeat';
				}
				if (borderLeft != fK)
					borderLeft = fK;
				if (borderRight != fL)
					borderRight = fL;
				if (borderTop != fM)
					borderTop = fM;
				if (borderBottom != fJ)
					borderBottom = fJ;
			}
		}
	var eJ = document.getElementById(fh.gk);
	var de = document.getElementById(fh.gh);
	var text = document.getElementById(fh.gu);
	var img = document.getElementById(fh.gm);
	if (img) {
		img.src = fh.ej;
	}
	if ((text)) {
		with (text.style) {
			with (fh.ek) {
				if (fontFamily != fO)
					fontFamily = fO;
				if (fontSize != fQ)
					fontSize = fQ;
				if (fontWeight != fU)
					fontWeight = fU;
				if (fontStyle != fR)
					fontStyle = fR;
				if (textDecoration != fS)
					textDecoration = fS;
				if (color != fN)
					color = fN;
			}
		}
	}
	if ((fh.ei) && eJ) {
		if (eJ.src != fh.ei)
			eJ.src = fh.ei;
	}
	if ((fh.eh) && de) {
		if (de.src != fh.eh)
			de.src = fh.eh;
	}
	if (fh.et)
		window.status = fh.et;
};
function al(ca) {
	if (ca == '') {
		return null;
	}
	for ( var i = 0; i <= ck.length - 1; i++) {
		if ((ck[i].id == ca) && (ck[i].gB == cm[cm.length - 1])) {
			return ck[i];
		}
	}
};
function ag(eD) {
	for ( var i = 0; i <= cj.length - 1; i++) {
		if (cj[i].gr == eD) {
			return cj[i];
		}
	}
};
function an(eD) {
	var eR = cj.length;
	for ( var i = 0; i <= eR - 1; i++) {
		if ((cj[i].id == eD) && (cj[i].gB == cm[cm.length - 1])) {
			return cj[i];
		}
	}
};
function FreeLayer(bD, cJ, cA, ce, cU, cV, padding, cI, borderLeft,
		borderRight, borderTop, borderBottom, bgColor, G, aX, ba, bv, filter,
		cD, shadowColor, cC, k) {
	var cX;
	var gF = an(cJ);
	var p = aG(filter);
	cX = {
		eN : true,
		id : bD,
		dM : (ce == null && gF) ? gF.dM : ce,
		en : (cA == null && gF) ? gF.en : cA,
		eB : cU == null && gF ? gF.eB : cU,
		eC : cV == null && gF ? gF.eC : cV,
		dG : cI == null && gF ? gF.dG : cI,
		dF : padding == null && gF ? gF.dF : padding,
		eb : borderLeft == null && gF ? gF.eb : borderLeft,
		ec : borderRight == null && gF ? gF.ec : borderRight,
		ed : borderTop == null && gF ? gF.ed : borderTop,
		ea : borderBottom == null && gF ? gF.ea : borderBottom,
		dA : bgColor == null && gF ? gF.dA : bgColor,
		dB : G == null && gF ? gF.dB : G,
		dC : aX == null && gF ? gF.dC : aX,
		dD : ba == null && gF ? gF.dD : ba,
		eq : cD == null && gF ? gF.eq : cD,
		eo : shadowColor == null && gF ? gF.eo : shadowColor,
		ep : cC == null && gF ? gF.ep : cC,
		dw : k == null && gF ? gF.dw : k,
		dJ : bv,
		dN : filter == null && gF ? gF.dN : aU(filter),
		gr : null,
		eW : 0,
		eV : 0,
		gb : ck[ck.length - 1],
		gB : cm[cm.length - 1]
	};
	ck[ck.length - 1].di = cX;
	bE[bE.length] = ck[ck.length - 1];
	cj[cj.length] = cX;
	co[co.length] = cX;
};
function InitDiv() {
	co.length = co.length - 1;
};
function am(gr) {
	for ( var i = 0; i <= ck.length - 1; i++) {
		if (ck[i].gr == gr) {
			return ck[i];
		}
	}
};
function itemClick(e, gD, fc, dc) {
	bo = true;
	itemOver(e, gD, fc, dc);
	if (cj[cm[fc]].cW) {
		if (gD.parentNode) {
			gD.parentNode.click();
		}
	} else {
		if (gD.fb.ez != '') {
			gD.parentNode.href = '#';
			gD.parentNode.target = '_self';
			alert('\x48\x79\x70\x65\x72\x6C\x69\x6E\x6B\x73\x20\x61\x72\x65\x20\x73\x75\x70\x70\x6F\x72\x74\x65\x64\x20\x6F\x6E\x6C\x79\x20\x69\x6E\x20\x52\x65\x67\x69\x73\x74\x65\x72\x65\x64\x20\x56\x65\x72\x73\x69\x6F\x6E\x2E');
		}
	}
	aS(e);
	return false;
};
function ao(dc) {
	var result = [];
	var gK = ap(dc);
	if (!gK)
		return result;
	for ( var i = 0; i <= cE.length - 1; i++) {
		if (!az(cE[i], gK)) {
			result[result.length] = cE[i];
			log('hide group ' + cE[i].gb.dE);
		}
	}
	return result;
};
function ap(dc) {
	if (!cn)
		return;
	log('current item is ' + cn.dE);
	var result = [];
	result[0] = cn.ga;
	result[1] = cn.di;
	var t = cn.gb;
	while (t) {
		result[result.length] = t.ga;
		t = t.gb;
	}
	for ( var i = 0; i <= result.length - 1; i++) {
		if (result[i])
			log('need show groups' + result[i].id);
	}
	return result;
};
function aH(eE) {
	var fV = eE;
	if ((fV == null) || (fV == null))
		return false;
	var eX = eE.fb;
	{
		if ((fV.filters == null)) {
			fV.style.visibility = 'hidden';
			return false;
		}
		if (fV.filters[1]) {
			if (fV.filters[1].status == 2)
				return;
			fV.filters[1].apply();
		}
		fV.style.visibility = 'hidden';
		if (fV.filters[1])
			fV.filters[1].play();
	}
};
function aw(eF, fb) {
	if (!eF)
		return;
	if (!fb) {
		var t = eF.fb;
	} else {
		var t = fb;
	}
	if (t && !t.aD) {
		aO(document.getElementById(t.gb.gr));
	}
	aH(eF);
};
function av() {
	for ( var i = 0; i <= cE.length - 1; i++) {
		aw(document.getElementById(cE[i].gr));
		I(cE[i], cE);
	}
};
function hideMenuGroup(dc) {
	if (!bJ) {
		bo = false;
		av();
		if (cj[bw].dI != '') {
			aw(ci.gq, ci);
		}
	} else {
		var eO = ao(dc);
		for ( var i = 0; i <= eO.length - 1; i++) {
			log('truly hide ' + eO[i].gb.dE);
			aw(document.getElementById(eO[i].gr));
			cE = I(eO[i], cE);
		}
	}
};
function itemOut(e, gD, dc) {
	if (gD == null)
		return;
	if (bw == -1)
		return;
	if (gD.dX == 2)
		return;
	log('----' + gD.id + 'out ----');
	clearTimeout(bI);
	aO(gD);
	if (bA)
		return;
	if ((!bQ) && (!cc)) {
		bI = setTimeout(function() {
			hideMenuGroup(dc);
		}, cj[bw].dQ * 1);
	} else {
		bI = setTimeout('hideMenuGroup(null)', cj[bw].dQ * 1);
	}
	log('----' + gD.id + 'out end----');
	bJ = false;
	aS(e);
};
function ai(eE) {
	var result = [];
	if (!eE.fb) {
		var fV = ag(eE.parentNode.id);
		if (fV) {
			var t = fV.gb;
			eE.fb = fV;
		}
	} else {
		var t = eE.fb.gb;
	}
	while (t) {
		result[result.length] = document.getElementById(t.gr);
		t = t.gb;
	}
	return result;
};
function groupOver(e, gD, fc, dc) {
	bJ = true;
	if (cg == gD)
		return;
	cg = gD;
	var t;
	if (bA)
		return;
	for ( var i = 0; i <= dc.length - 1; i++) {
		t = ck[dc[i]];
		o(document.getElementById(t.gr), t);
	}
	aS(e);
};
function groupOut(e, gD, dc) {
	log(gD.id + ' group out');
	itemOut(e, cn, dc);
	bJ = false;
	aS(e);
};
function itemOver(e, gD, fc, dc) {
	log('-----' + gD.id + 'over------');
	bJ = true;
	clearTimeout(cL);
	bw = cm[fc];
	eY = ck[dc[0] * 1];
	var gy = cj[bw];
	if (eY) {
		cn = eY;
	}
	log('memItem set' + eY);
	if (eY.dX == 2) {
		return;
	}
	log('var init');
	if ((gy.dH == '1') || (gy.dH == '') || bo) {
		o(gD, eY);
	}
	log('setOverStyle');
	var di = eY.di;
	if ((gy.dH != '') && !bo)
		return;
	if (bA)
		return;
	if (cu)
		return;
	if ((eY) && (di)) {
		cE[cE.length] = eY.di;
		if (!di.gr) {
			log('get phyobj');
			var eE = document.getElementById(di.gr);
			if (!eE) {
				log('create group');
				eE = N(eY.di, fc);
			}
			log('assign group');
			di.gr = eE.id;
			eE.fb = di;
		}
		{
			eE = document.getElementById(di.gr);
		}
		log('begin show timer');
		if ((!bQ) && (!cc)) {
			cL = setTimeout(function() {
				P(eE, gD);
			}, gy.es);
		} else {
			setTimeout('delayShowIe5("' + di.gr + '","' + gD.id + '")', gy.es);
		}
		log('show timer set');
	}
	log('-----' + gD.id + 'over end------');
};
function delayShowIe5(gj, gs) {
	var eE = document.getElementById(gj);
	var gD = document.getElementById(gs);
	P(eE, gD);
};
function W(eX) {
	var result = 'position:absolute;visibility:hidden;';
	if (bP) {
		if (eX.dN != '') {
			if (!bQ) {
				result += eX.dN;
			} else {
			}
		} else {
			result += 'filter:';
		}
		if (eX.eq != '') {
			if (eX.eq == 0) {
				if (!bQ) {
					result += ' progid:DXImageTransform.Microsoft.DropShadow(color='
							+ eX.eo
							+ ',offX='
							+ eX.ep
							+ ',offY='
							+ eX.ep
							+ ',positive=true);';
				} else {
				}
			} else if (eX.eq == 1) {
				if (!bQ) {
					result += ' progid:DXImageTransform.Microsoft.Shadow(color='
							+ eX.eo + ',direction=135,strength=' + eX.ep + ');';
				} else {
				}
			}
		}
	}
	return result;
};
function aV(filter) {
	var result;
	var s = filter.toLowerCase();
	var fs = s.split(' ');
	var code = 23;
	result = 'filter:';
	for ( var i = 0; i <= fs.length - 1; i++) {
		t = fs[i];
		if (t.indexOf('iris') != -1) {
			if (t.indexOf('irisstyle=square') != -1) {
				if (t.indexOf('motion=in') != -1) {
					code = 0;
				} else {
					code = 1;
				}
			} else if (t.indexOf('irisstyle=circle') != -1) {
				if (t.indexOf('motion=in') != -1) {
					code = 2;
				} else {
					code = 3;
				}
			}
		} else if (t.indexOf('blinds') != -1) {
			if (t.indexOf('direction=up') != -1) {
				if (t.indexOf('bands=1') != -1) {
					code = 4;
				}
			} else if (t.indexOf('direction=right') != -1) {
				if (t.indexOf('bands=1') != -1) {
					code = 6;
				} else {
					code = 8;
				}
			} else if (t.indexOf('direction=down') != -1) {
				if (t.indexOf('bands=1') != -1) {
					code = 5;
				} else {
					code = 9;
				}
			} else if (t.indexOf('direction=left') != -1) {
				if (t.indexOf('bands=1') != -1) {
					code = 7;
				}
			}
		} else if (t.indexOf('checkerboard') != -1) {
			if (t.indexOf('direction=right') != -1) {
				code = 10;
			} else {
				code = 11;
			}
		} else if (t.indexOf('randomdissolve') != -1) {
			code = 12;
		} else if (t.indexOf('barn') != -1) {
			if (t.indexOf('orientation=vertical') != -1) {
				if (t.indexOf('motion=in') != -1) {
					code = 13;
				} else {
					code = 14;
				}
			} else {
				if (t.indexOf('motion=in') != -1) {
					code = 15;
				} else {
					code = 16;
				}
			}
		} else if (t.indexOf('strips') != -1) {
			if (t.indexOf('leftdown') != -1) {
				code = 17;
			} else if (t.indexOf('leftup') != -1) {
				code = 18;
			} else if (t.indexOf('rightdown') != -1) {
				code = 19;
			} else if (t.indexOf('rightup') != -1) {
				code = 20;
			}
		} else if (t.indexOf('randombars') != -1) {
			if (t.indexOf('orientation=horizontal') != -1) {
				code = 21;
			} else {
				code = 22;
			}
		}
		var eK = t.indexOf('duration=');
		var ds = t.substr(eK, t.length);
		result += 'revealTrans(transition=' + code + ',' + ds + ' ';
	}
	return result;
};
function N(eX, gB) {
	var result, fX = '';
	eD = 'g' + ay();
	fX += "<div class='umdiv' id=\"" + eD + "\" style=\"" + W(eX) + "\"";
	if (bR) {
		if (eX.eq != '') {
			fX += ' style="padding:' + eX.ep + 'px"';
		}
	}
	fX += " >";
	fX += aE(eX, gB, false);
	fX += "</div>";
	var b = document.getElementsByTagName('BODY')[0];
	var d = document.createElement('DIV');
	d.style.visiblity = 'hidden';
	d.innerHTML += fX;
	d.style.position = 'absolute';
	d.style.left = 0;
	d.style.top = 0;
	b.appendChild(d);
	b.appendChild(d.childNodes[0]);
	b.removeChild(d);
	eX.gr = eD;
	result = document.getElementById(eD);
	aN(eX);
	aB(result);
	result.fb = eX;
	return result;
};
function aB(gi) {
	gi.style.zIndex = z + ay();
};
function aN(eX) {
	var dj = af(eX);
	var gE = [];
	var maxHeight = 0;
	var maxWidth = 0;
	var eR = dj.length;
	for ( var i = 0; i <= eR - 1; i++) {
		var t = document.getElementById(dj[i].gr);
		if (t) {
			t.fb = dj[i];
			aO(t);
			var dir = dj[i].ga.dM;
			if ((dj[i].dX == 2) && (dir != 'v')) {
				gE[gE.length] = t;
			}
			if (dir != 'v') {
				if (t.offsetHeight > maxHeight) {
					maxHeight = t.offsetHeight;
				}
			} else {
				if (t.offsetWidth > maxWidth) {
					maxWidth = t.offsetWidth;
				}
			}
		}
	}
	for ( var i = 0; i <= gE.length - 1; i++) {
		gE[i].style.height = gE[i].parentNode.offsetHeight + 'px';
		if (cc) {
			if (gE[i].fb.ee.indexOf('blank.gif') != -1) {
				document.getElementById(gE[i].fb.gk).style.height = 0 + 'px';
			}
		}
	}
	for ( var i = 0; i <= eR - 1; i++) {
		var t = document.getElementById(dj[i].gr);
		if (t) {
			var dir = dj[i].ga.dM;
			if (dir != 'v') {
				if (t.offsetHeight != maxHeight)
					t.style.height = maxHeight + 'px';
			} else {
			}
		}
	}
};
function ar(fa) {
	var result = [];
	result[0] = document.getElementById(fa.gr);
	var t = fa.gb;
	while (t) {
		result[result.length] = document.getElementById(t.gr);
		t = t.gb;
	}
	return result;
};
function ae(eH) {
	var t = eH.getElementsByTagName('TABLE');
	return t[0];
};
function P(eE, gD) {
	if (!cn)
		return;
	log('delay show begin');
	if ((cn.gr == gD.id) && bJ) {
		log('delay show sender id is ' + gD.id);
		if (eE.style.visibility == 'hidden') {
			log('setpos begin');
			aQ(eE, gD);
			aI(eE);
			log('set pos end');
		}
	}
	log('delay show end');
};
function aG(filter) {
	if (!filter)
		return [];
	var result = [], t = [];
	filter = filter.toLowerCase();
	if ((filter != '') && (filter.substr(0, 6) != 'filter')) {
		t = filter.split(' ');
		result[0] = t[0].substr(0, 1);
		result[1] = t[0].substr(1, 1);
		result[2] = t[0].substr(2, 3);
		result[3] = t[1].substr(0, 1);
		result[4] = t[1].substr(1, 1);
		result[5] = t[1].substr(2, 3);
	}
	return result;
};
function aI(eE) {
	var fV = eE;
	var t = [];
	if ((fV == null) || (fV == null))
		return false;
	if ((bQ) && (fV.filters[0])) {
		fV.style.visibility = 'visible';
		return false;
	}
	if (t.length > 0) {
	} else {
		if ((fV.filters == null)) {
			fV.style.visibility = 'visible';
			return false;
		}
		fV.style.visibility = 'hidden';
		if (fV.filters[0])
			fV.filters[0].apply();
		fV.style.visibility = 'visible';
		if (fV.filters[0])
			fV.filters[0].play();
	}
};
function aD(fd) {
	return fd.fb.ga.aD == true;
};
function aK(target, fg, fj) {
	var h = U();
	var w = V();
	var gL = at();
	var gG = as();
	w += gG;
	h += gL;
	if (target.offsetLeft + target.offsetWidth > w) {
		target.style.left = w - target.offsetWidth + 'px';
	}
	if ((target.offsetTop + target.offsetHeight > h)) {
		target.style.top = h - target.offsetHeight + 'px';
	}
	if (target.offsetTop < gL) {
		target.style.top = gL + 'px';
	}
	if (target.offsetLeft < gG) {
		target.style.left = gG + 'px';
	}
};
function hasRelativeParent(Item) {
	var t = Item;
	while (t.parentNode) {
		var styl = au(t, 'position');
		if (styl == 'relative') {
			return true;
		}
		t = t.parentNode;
	}
	return false;
};
function aQ(gT, gb) {
	log('set pos begin inside');
	var gc = S(gb);
	var gd = T(gb);
	log('pos found');
	var dh = dg = 0;
	var gy = cj[bw];
	log('root group get');
	var left, top;
	var tMemObj;
	if ((bP || cv || cB) && (gy.er == '') && (gy.dI == '')) {
		if (aD(gb)) {
			var dh = document.getElementsByTagName('BODY')[0].offsetTop;
			var dg = document.getElementsByTagName('BODY')[0].offsetLeft;
		}
	}
	if (hasRelativeParent(gb)) {
		if (bP) {
			if (document.documentElement
					&& document.documentElement.clientHeight) {
				dh = dg = 0;
			} else {
				dg = -parseInt(au(document.getElementsByTagName('BODY')[0],
						'margin-Left'));
			}
		}
		if (ct) {
		}
	}
	log('body margin set');
	var fb = gb.fb.ga;
	if (gT.fb.en == '5') {
		if (fb.dM == 'v') {
			gT.fb.en = '6'
		} else {
			gT.fb.en = '2';
		}
	}
	switch (gT.fb.en) {
	case '2': {
		left = gc + dg;
		top = gd + dh + gb.offsetHeight;
		break;
	}
	case '6': {
		left = gc + dg + gb.offsetWidth;
		top = gd + dh;
		break;
	}
	case '4': {
		left = gc + dg - gT.offsetWidth;
		top = gd + dh;
		break;
	}
	case '8': {
		left = gc + dg;
		top = gd + dh - gT.offsetHeight;
		break;
	}
	}
	log('pos set');
	tMemObj = gT.fb;
	var xf = tMemObj.eB * 1;
	var yf = tMemObj.eC * 1;
	if (!isNaN(xf)) {
		left += xf;
	}
	if (!isNaN(yf)) {
		top += yf;
	}
	if (bR) {
		if (tMemObj.eq != '') {
			left -= tMemObj.ep;
			top -= tMemObj.ep;
		}
	}
	gT.style.left = left + 'px';
	gT.style.top = top + 'px';
	aK(gT, dg, dh);
	log('set pos end inside');
};
function ah(eE) {
	for ( var i = 0; i <= ck.length - 1; i++) {
		if (ck[i].di == eE) {
			return ck[i];
		}
	}
};
function ad(item) {
};
function af(eE) {
	var result = [];
	for ( var i = 0; i <= ck.length - 1; i++) {
		if (ck[i].ga == eE) {
			result[result.length] = ck[i];
		}
	}
	return result;
};
function aA() {
	var t, gZ;
	t = cj[cm[bp]];
	aN(t);
	gZ = document.getElementById(t.gr);
	gZ.fb = t;
	aB(gZ);
	var fY = gZ.parentNode.parentNode.parentNode.parentNode;
	aM(t, fY, t.er);
	if (t.dI != '') {
		fY.style.position = 'absolute';
		fY.style.visibility = 'hidden';
		ci = t;
		ci.gq = fY;
		document.oncontextmenu = function(event) {
			M(event);
			return false;
		}
	} else {
		aI(fY);
	}
	bp++;
};
function M(e) {
	clearTimeout(bu);
	var t = ci.gq;
	var gw = 0;
	var gx = 0;
	if (!e)
		var e = window.event;
	if (e.pageX || e.pageY) {
		gw = e.pageX;
		gx = e.pageY;
	} else if (e.clientX || e.clientY) {
		gw = e.clientX + as();
		gx = e.clientY + at();
	}
	t.style.left = gw + 'px';
	t.style.top = gx + 'px';
	aI(t);
	bu = setTimeout("hideContext()", ci.dQ);
	return false;
};
function hideContext() {
	if (!bJ) {
		aw(ci.gq, ci);
	}
};
function aM(fb, fV, scroll) {
	if (scroll != '') {
		var gZ = fV, t = fb;
		if (cw) {
			var bl = au(gZ, 'border-left-width').split('p')[0] * 1;
			var br = au(gZ, 'border-right-width').split('p')[0] * 1;
			var fZ = gZ.offsetWidth - bl - br;
		}
		gZ.style.position = 'absolute';
		if (cw)
			gZ.style.width = fZ + 'px';
		gZ.style.left = t.fx + 'px';
		gZ.style.top = t.fy + 'px';
		var gW = gZ.id;
		if (!bQ) {
			switch (t.er) {
			case 'x': {
				window.setInterval(function() {
					aL(gZ, true, false)
				}, 1);
				break;
			}
			case 'y': {
				window.setInterval(function() {
					aL(gZ, false, true)
				}, 1);
				break;
			}
			case 'xy': {
				window.setInterval(function() {
					aL(gZ, true, true)
				}, 1);
				break;
			}
			}
		} else {
			switch (t.er) {
			case 'x': {
				window.setInterval('scrollMenuIe5("' + gW + '",true,false)', 1);
				break;
			}
			case 'y': {
				window.setInterval('scrollMenuIe5("' + gW + '",false,true)', 1);
				break;
			}
			case 'xy': {
				window.setInterval('scrollMenuIe5("' + gW + '",true,true)', 1);
				break;
			}
			}
		}
	}
};
function scrollMenuIe5(gW, fD, fE) {
	var t = document.getElementById(gW);
	aL(t, fD, fE);
};
function aa(eE) {
	var result = '';
	if ((eE.eu != '') && (eE.eu)) {
		result += 'width:' + eE.eu + '; ';
	} else {
		result += 'width:auto;';
	}
	result += 'visibility:inherit;position:static;';
	result += X(eE, true);
	return result;
};
function genShadowCss(eX) {
	var result = '';
	if (eX.eq == '')
		return result;
	if (eX.eq == 0) {
		result += ' progid:DXImageTransform.Microsoft.DropShadow(color='
				+ eX.eo + ',offX=' + eX.ep + ',offY=' + eX.ep
				+ ',positive=true);';
	} else if (eX.eq == 1) {
		result += ' Shadow(color=' + eX.eo + ',direction=135,strength=' + eX.ep
				+ ');';
	}
	return result;
};
function genFilterCss(eX) {
	result = '';
	if (eX.dN != '') {
		result += eX.dN + ' ';
	}
	if ((eX.dw != '') && (eX.dw * 1 != 100)) {
		if (!bQ) {
			result += 'progid:DXImageTransform.Microsoft.Alpha(opacity='
					+ eX.dw + ')  ';
		} else {
		}
	}
	return result;
};
function K() {
	if ((cs) && (!document.getElementById))
		return;
	var fX = "";
	if (bp == -1) {
		bp = 0;
	}
	var gM = cm[bp];
	var eS = cj[gM];
	var fe = eD = "";
	eD = 'g' + ay();
	var width = '100%';
	if ((eS.dI != '') || (eS.er != '') || (eS.dO == 'left')) {
		width = 'auto';
	}
	var sizeForShadow = 0;
	if (bP) {
		sizeForShadow = eS.ep
	}
	fX += '<table class="umtable" cellspacing="0" cellpadding="'
			+ sizeForShadow + '"  style="border:none;position:static;width:'
			+ width + ';';
	fX += genFilterCss(eS);
	fX += genShadowCss(eS);
	fX += '" ><tr class="umtr"><td class="umtd">';
	fX += '<table cellspacing="0" cellpadding="0" class="umtable" id="' + eD
			+ '"' + ' align="' + cj[gM].dO + '"' + ' style="' + aa(cj[gM])
			+ ';"' + '><tr class="umtr"><td class="umtd"' + '>';
	fX += aE(cj[gM], bp, true);
	fX += '</td></tr></table></td></tr></table>';
	cj[gM].gr = eD;
	document.write(fX);
};
function ab(eY) {
	var result = [];
	result[0] = eY.eK;
	var t = eY.gb;
	while (t) {
		result[result.length] = t.eK;
		t = t.gb;
	}
	return result;
};
function aj(eE) {
	var result = [];
	var t = eE.gb;
	while (t) {
		result[result.length] = t.eK;
		t = t.gb;
	}
	return result;
};
function J(gf) {
	var l = gf.length;
	var result = '[';
	for (i = 0; i <= l - 1; i++) {
		result += gf[i] + ',';
	}
	if (l > 0) {
		result = result.substr(0, result.length - 1);
	}
	result += ']';
	return result;
};
function aT(gN) {
	if (gN == '') {
		return '';
	}
	var result = '';
	var da = gN.split(' ');
	for (i = 0; i <= da.length - 1; i++) {
		result += da[i] + '&nbsp;';
	}
	return result;
};
function aE(eE, gA, fC) {
	var dj = af(eE);
	var fX = "";
	var eG = J(aj(eE));
	fX += "<table class='umtable' cellpadding=0" + " cellspacing=\"" + eE.dG
			+ "\"";
	if (!fC) {
		fX += " style=\"" + X(eE) + '" ';
	} else {
		fX += 'align="' + eE.dO + '" ';
	}
	if ((bR) && (eE.eq != '')) {
	}
	fX += ' onmouseover="groupOver(event,this,' + gA + ',' + eG
			+ ');" onmouseout="groupOut(event,this,' + eG + ')"' + ' >';
	if (eE.dM != 'v') {
		fX += "<tr class='umtr'>";
	}
	var eR = dj.length;
	for ( var i = 0; i <= eR - 1; i++) {
		var c = dj[i];
		fe = "r" + ay();
		if (eE.dM == 'v') {
			fX += "<tr class='umtr'>";
		}
		var gZ;
		if (c.dX == 2) {
			gZ = 0;
		} else {
			gZ = eE.dF;
		}
		var link = Z(c);
		fX += "<td class='umtd'>";
		fX += link;
		fX += "<table class='umtable'" + " cellspacing=\"0\" "
				+ " cellpadding=\"" + gZ + "\" " + " class=\"astb\""
				+ " style=\"" + Y(c, gA) + "\"" + " id=\"" + fe + "\""
				+ ' title="' + c.ey + '"';
		{
			var ge = J(ab(c));
			fX += 'onclick="itemClick(event,this,' + gA + ',' + ge
					+ ');" onmouseover="itemOver(event,this,' + gA + ',' + ge
					+ ')" onmouseout="itemOut(event,this,' + ge + ')"';
		}
		fX += " >";
		fX += "<tr>";
		switch (c.dX) {
		case 0: {
			fX += H(c.ee, c.dS, c.dT, c, true);
			var bn = 'asmt' + ay();
			fX += "<td class='umtd'" + ' style="' + ac(c) + '"' + ' valign="'
					+ c.dv + '"' + ' id="' + bn + '"' + " >";
			fX += aT(c.dE);
			fX += "</td>";
			c.gu = bn;
			fX += H(c.dZ, c.dy, c.dz, c, false);
			break;
		}
		case 1: {
			fX += H(c.ee, c.dS, c.dT, c, true);
			var bn = 'asimgitem' + ay();
			fX += "<td class='umtd'" + ' style="' + ac(c) + '"' + ' valign="'
					+ c.dv + '"' + " >";
			fX += A(c, bn);
			fX += "</td>";
			c.gm = bn;
			fX += H(c.dZ, c.dy, c.dz, c, false);
			break;
		}
		case 2: {
			fX += H(c.ee, c.dS, c.dT, c, true);
			break;
		}
		case 3: {
			fX += H(c.ee, c.dS, c.dT, c, true);
			var bn = 'asmt' + ay();
			fX += "<td class='umtd'" + ' style="' + ac(c) + '"' + ' valign="'
					+ c.dv + '"' + ' id="' + bn + '"' + " >";
			fX += c.dE;
			fX += "</td>";
			c.gu = bn;
			fX += H(c.dZ, c.dy, c.dz, c, false);
			break;
		}
		}
		fX += "</tr>";
		fX += "</table>";
		if ((!bP) && (link != '')) {
			fX += "</a>";
		}
		fX += "</td>";
		if (eE.dM == 'v') {
			fX += "</tr>";
		}
		c.gr = fe;
	}
	if (eE.dM != 'v') {
		fX += "</tr>";
	}
	fX += "</table>";
	var pou = [];
	if (cc) {
		fX += '<div class="umdiv" style="position:absolute;visibility:hidden;left:0;top:0">';
		for ( var i = 0; i <= dj.length - 1; i++) {
			var tmpSrc = dj[i].ei;
			if (!az(tmpSrc, pou)) {
				fX += '<img src="' + tmpSrc + '">';
				pou[pou.length] = tmpSrc;
			}
			var tmpSrc = dj[i].ee;
			if (!az(tmpSrc, pou)) {
				fX += '<img src="' + tmpSrc + '">';
				pou[pou.length] = tmpSrc;
			}
			var tmpSrc = dj[i].eh;
			if (!az(tmpSrc, pou)) {
				fX += '<img src="' + tmpSrc + '">';
				pou[pou.length] = tmpSrc;
			}
			var tmpSrc = dj[i].dZ;
			if (!az(tmpSrc, pou)) {
				fX += '<img src="' + tmpSrc + '">';
				pou[pou.length] = tmpSrc;
			}
		}
		fX += '</div>';
	}
	return fX;
};
function linkOver(eK) {
	if (ck[eK].et) {
		status = ck[eK].et;
	} else {
		status = ck[eK].ez;
	}
	if (bP) {
		document.df = true;
	}
};
function R(gN) {
	var da = gN.split('"');
	var result = gN;
	if (da.length >= 1) {
		result = '';
		for ( var i = 0; i <= da.length - 1; i++) {
			if (i <= da.length - 2) {
				result += da[i] + "'";
			} else {
				result += da[i];
			}
		}
	} else {
	}
	return result;
};
function Z(eY) {
	var result = '';
	var url = eY.ez;
	if (url != '') {
		result += '<a class="uma" href="' + R(url) + '" ';
		var target = eY.ex;
		if (target != '') {
			result += ' target="' + target + '"';
		}
		result += ' style="text-decoration:none;" onmouseover="linkOver('
				+ eY.eK + ');return document.df;" ';
		result += '>';
	}
	return result;
};
function X(eE, isMain) {
	var result = '';
	if (eE.eb != '') {
		result += 'border-left:' + eE.eb + ';';
	}
	if (eE.ec != '') {
		result += 'border-right:' + eE.ec + ';';
	}
	if (eE.ed != '') {
		result += 'border-top:' + eE.ed + ';';
	}
	if (eE.ea != '') {
		result += 'border-bottom:' + eE.ea + ';';
	}
	if (eE.dA != '') {
		result += 'background-color:' + eE.dA + ';';
	}
	if (eE.dB) {
		result += 'background-image:url(' + eE.dB + ');';
		if ((eE.dC == true) && (eE.dD == true)) {
			result += 'background-repeat:';
			result += 'repeat;';
		} else if (eE.dC == true) {
			result += 'background-repeat:';
			result += 'repeat-x;';
		} else if (eE.dD == true) {
			result += 'background-repeat:';
			result += 'repeat-y;';
		} else {
			result += 'background-repeat:';
			result += 'no-repeat;';
		}
	}
	if (bP && !isMain) {
		genFilterCss(eE);
	} else {
	}
	result += ';';
	return result;
};
function ac(eY) {
	var result = '';
	if (eY.du) {
		result += 'text-align:' + eY.du + ';';
	}
	result += 'white-space:nowrap;';
	return result;
};
function aC(cursor) {
	var dm = [ 'default', 'pointer', 'crosshair', 'help', 'text', 'move',
			'wait', 'hand' ];
	var gN = cursor.toLowerCase();
	return az(gN, dm);
};
function Y(eY, gA) {
	var result = "";
	result = "width:100%;";
	if (eY.dX == 2) {
		if (eY.ga.dM == 'v') {
			eY.eA = '';
		} else {
			eY.dP = '';
		}
	}
	if (eY.dP > 0) {
		result += "height:" + eY.dP + 'px;';
	}
	if (eY.eA > 0) {
		result += "width:" + eY.eA + 'px;';
	}
	if (eY.dX != 2) {
		var by = cj[cm[gA]].dL;
		var cursor = cj[cm[gA]].dK;
		if (eY.ez != '') {
			if (aC(by)) {
				result += ' cursor:' + by + ';';
			} else {
				result += ' cursor:url(' + by + '),' + by + ',pointer;';
			}
		} else {
			if (aC(cursor)) {
				result += ' cursor:' + cursor + ';';
			} else {
				result += ' cursor:url(' + cursor + '),default;';
			}
		}
	}
	return result;
};
function A(eY, id) {
	var result = '';
	var th = ha = 'px';
	if (eY.dV == 'auto') {
		th = ''
	}
	if (eY.dW == 'auto') {
		ha = ''
	}
	result += '<img ' + ' id="' + id + '" ' + ' style="height:' + eY.dV + th
			+ '; ' + ' width:' + eY.dW + ha + '; ' + ' border:' + eY.dU
			+ 'px solid #000000;"' + ' src="' + eY.ef + '"' + '>';
	return result;
};
function H(src, height, width, eY, eJ) {
	if ((height == '') && (width == '') && (eY.dX != 2))
		return '';
	if ((height == -1) && (width == -1) && (eY.dX != 2)) {
		if (eY.ga.dM != 'v') {
			return '';
		} else if ((!eJ) && (eY.ga.eV == 0)) {
			return '';
		} else if ((eJ) && (eY.ga.eW == 0)) {
			return '';
		}
	}
	var eL = 'asimg' + ay();
	var fX = "";
	var hb = 0;
	if ((width == '') || (width == -1) || (width == 0)) {
		hb = 1;
	} else {
		hb = width;
	}
	if ((eJ) && (eY.ga.dM == 'v')) {
		if (eY.ga.eW == 0) {
			hb = 1;
		} else {
			hb = eY.ga.eW;
		}
	} else if (eY.ga.dM == 'v') {
		if (eY.ga.eV == 0) {
			hb = 1;
		} else {
			hb = eY.ga.eV;
		}
	}
	fX += '<td class="umtd" style="width:' + hb + 'px;">';
	if ((src != "")) {
		var border = 'none';
		if ((eJ) && eY.dR)
			border = eY.dR * 1 + 'px solid #000000';
		if ((!eJ) && eY.dx)
			border = eY.dx * 1 + 'px solid #000000';
		fX += "<img " + ' id="' + eL + '" ' + " src=\"" + src
				+ "\" style=\"border:" + border + "\"";
		if (height >= 0) {
			fX += "height=\"" + height + "\" ";
		}
		if (width >= 0) {
			fX += "width =\"" + width + "\" ";
		}
		fX += "/>";
	}
	fX += "</td>";
	if (eJ) {
		eY.gk = eL;
	} else {
		eY.gh = eL;
	}
	return fX;
};
function ay() {
	return ++bZ;
};
function I(fV, da) {
	var result = [];
	for ( var i = 0; i <= da.length - 1; i++) {
		if (da[i] != fV) {
			result[result.length] = da[i];
		}
	}
	return result;
};
function az(fV, f) {
	for ( var i = 0; i <= f.length - 1; i++) {
		if (fV == f[i]) {
			return true
		}
	}
	return false;
};
function log(fi) {
	var fW = document.getElementById("debugHint");
	if ((cf) && (fW)) {
		fW.value = fi + "\r" + "\n" + fW.value;
	}
};
function S(fV) {
	var dn = 0;
	if (fV.offsetParent) {
		while ((fV.offsetParent)) {
			dn += fV.offsetLeft;
			fV = fV.offsetParent;
			if (fV == null)
				break;
		}
	} else if (fV.x)
		dn += fV.x;
	return dn;
};
function T(fV) {
	var dp = 0;
	if (fV.offsetParent) {
		while ((fV.offsetParent)) {
			dp += fV.offsetTop;
			fV = fV.offsetParent;
			if (fV == null)
				break;
		}
	} else if (fV.y)
		dp += fV.y;
	return dp;
};
function au(el, gP) {
	if ((cy) || cx || bP) {
		var gX = gP.split('-');
		var gY = '';
		if (gX.length > 0) {
			gP = "";
			for ( var i = 0; i <= gX.length - 1; i++) {
				if (i >= 1) {
					gY = gX[i].substr(0, 1);
					gY = gY.toUpperCase();
					gY += gX[i].substring(1);
				} else {
					gY = gX[i];
				}
				gP += gY;
			}
		} else {
		}
	}
	var x = el;
	if (bS || bQ) {
		var y = x.currentStyle[gP]
	} else if (bT) {
		var y = eval('x.currentStyle.' + gP);
	} else if (cx || cy) {
		var y = eval('getComputedStyle(x,null).' + gP);
	} else {
		var y = document.defaultView.getComputedStyle(x, null)
				.getPropertyValue(gP);
	}
	return y;
};
function O() {
	var fB = navigator.userAgent;
	var hd = navigator.appVersion;
	log('navigator is: ' + fB);
	log('version is:' + hd);
	if (hd.indexOf('MSIE 5.5') != -1) {
		bR = true;
	}
	if (hd.indexOf('MSIE 5.0') != -1) {
		bQ = true;
	}
	if ((hd.indexOf('IE') != -1) && (fB.indexOf('Opera') == -1)) {
		bP = true;
	}
	if (fB.indexOf('Opera/9.00') != -1) {
		cy = true;
	}
	if (fB.indexOf('Opera 8') != -1) {
		cx = true;
	}
	if (fB.indexOf('Opera') != -1) {
		cv = true
	}
	if (fB.indexOf('Opera 7') != -1) {
		cw = true
	}
	if (fB.indexOf('Safari') != -1) {
		cB = true;
	}
	if (fB.indexOf('Netscape/7') != -1) {
		ct = true;
	}
	if (fB.indexOf('Netscape/6') != -1) {
		cs = true;
	}
	if (fB.indexOf('MSIE 7.0') != -1) {
		bT = true;
	}
	if ((fB.indexOf('MSIE 6') != -1) && (fB.indexOf('Opera') == -1)) {
		bS = true;
	}
	if (fB.indexOf('KHTML/3') != -1) {
		cd = true;
	}
	if (fB.indexOf('Konqueror') != -1) {
		cc = true;
	}
	if (fB.indexOf('Firefox') != -1) {
		ff = true;
	}
};
function aP(fV, value) {
	fV.style.opacity = value / 100;
	if (bP) {
		fV.style.filter += ' alpha(opacity=' + value + ')';
	}
};
function aS(e) {
	if (!e)
		var e = window.event;
	e.cancelBubble = true;
	if (e.stopPropagation)
		e.stopPropagation();
};
function all(item) {
	if (!item)
		return;
	var result;
	if (bQ) {
		result = item.all;
	} else {
		result = item.getElementsByTagName("*");
	}
	return result;
};
function V() {
	var value;
	if (self.innerHeight) {
		value = self.innerWidth;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) {
		value = document.documentElement.clientWidth;
	} else if (document.body) {
		value = document.body.clientWidth;
	}
	return value;
};
function U() {
	var value;
	if (self.innerHeight) {
		value = self.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) {
		value = document.documentElement.clientHeight;
	} else if (document.body) {
		value = document.body.clientHeight;
	}
	return value;
};
function at() {
	if (document.documentElement && document.documentElement.scrollTop) {
		dr = document.documentElement.scrollTop
	} else if (document.body) {
		dr = document.body.scrollTop;
	}
	return dr;
};
function as() {
	if (document.documentElement && document.documentElement.scrollLeft) {
		dq = document.documentElement.scrollLeft
	} else if (document.body) {
		dq = document.body.scrollLeft;
	}
	return dq;
};
function slideMove2(item, show, gI, direction, eM) {
	if (!item)
		return;
	var gR = 100 / gI;
	if (eM) {
		var eT = item;
		item = eT.childNodes[0];
		var fT = item.offsetTop, fP = item.offsetLeft;
		item.fT = fT;
		item.fP = fP;
		var gW;
		item.gQ = 0;
		item.gS = 0;
		var gC = 'rect(0px,' + item.offsetWidth + 'px,' + item.offsetHeight
				+ 'px,0px)';
		eT.style.left = fP + 'px';
		eT.style.top = fT + 'px';
		eT.style.height = 1 + 'px';
		eT.style.width = 1 + 'px';
		eT.style.clip = gC;
		eT.style.backgroundColor = 'Transparent';
		eT.style.padding = 0;
		eT.style.margin = 0;
		item.eU = eT.id;
		log(gC);
		if (show) {
			switch (direction * 1) {
			case 2: {
				item.style.top = (item.offsetTop - item.offsetHeight) + 'px';
				eI = 0;
				break;
			}
			case 6: {
				item.style.left = (item.offsetLeft - item.offsetWidth) + 'px';
				vstep = 0;
				break;
			}
			case 8: {
				item.style.top = (item.offsetTop + item.offsetHeight) + 'px';
				eI = 0;
				break;
			}
			case 4: {
				item.style.left = (item.offsetLeft + item.offsetWidth) + 'px';
				vstep = 0;
				break;
			}
			}
		} else {
			switch (direction) {
			case 2: {
				item.gS = (item.offsetTop + item.offsetHeight);
				eI = 0;
				break;
			}
			case 6: {
				item.gQ = (item.offsetLeft + item.offsetWidth);
				vstep = 0;
				break;
			}
			case 8: {
				item.gS = (item.offsetTop - item.offsetHeight);
				eI = 0;
				break;
			}
			case 4: {
				item.gQ = (item.offsetLeft - item.offsetWidth);
				vstep = 0;
				break;
			}
			}
		}
		log('item init top is ' + item.style.top);
		item.style.visibility = 'visible';
	} else {
		var item = document.getElementById(item);
		var gz = document.getElementById(item.eU);
		switch (direction * 1) {
		case 2:
		case 6: {
			if (direction == 2) {
				item.vstep = Math.ceil(0.2 * (item.gS - item.offsetTop));
				item.eI = 0;
			} else {
				item.eI = Math.ceil(0.2 * (item.gQ - item.offsetLeft));
				item.vstep = 0;
			}
			log('vstep is ' + item.vstep);
			item.style.top = (item.offsetTop + item.vstep) + 'px';
			item.style.left = (item.offsetLeft + item.eI) + 'px';
			if ((item.offsetTop >= item.gS) && (item.offsetLeft >= item.gQ)) {
				log('slide move over oLeft is ' + fP + ' oTop is ' + fT);
				if (gz) {
				}
				item.style.top = item.fT + 'px';
				item.style.left = item.fP + 'px';
				if (!show) {
					item.style.visibility = 'hidden'
				}
				clearTimeout(gW);
				log('item final top is ' + item.offsetTop);
				return;
			}
			break;
		}
		case 8:
		case 4: {
			vstep = Math.ceil(0.2 * Math.abs(item.offsetTop - gS));
			eI = Math.ceil(0.2 * Math.abs(item.offsetLeft - gQ));
			item.style.top = (item.offsetTop - vstep) + 'px';
			item.style.left = (item.offsetLeft - eI) + 'px';
			if ((item.offsetTop <= gS) && (item.offsetLeft <= gQ)) {
				log('slide move over oLeft is ' + fP + ' oTop is ' + fT);
				eT.parentNode.replaceChild(item, eT);
				item.style.top = fT + 'px';
				item.style.left = fP + 'px';
				if (!show) {
					item.style.visibility = 'hidden'
				}
				clearTimeout(gW);
				log('item final top is ' + item.offsetTop);
				return;
			}
			break;
		}
		}
	}
	setTimeout('slideMove2("' + item.id + '",' + show + ',' + gI + ','
			+ direction + ',false)', gR);
};
function aR(item, show, gI, direction) {
	if (!item)
		return;
	var fT = item.offsetTop, fP = item.offsetLeft;
	item.fT = fT;
	item.fP = fP;
	var gR = 100 / gI;
	var gW;
	var gQ = gS = 0;
	var gC = 'rect(0px,' + item.offsetWidth + 'px,' + item.offsetHeight
			+ 'px,0px)';
	var eT = document.createElement('DIV');
	eT.style.position = 'absolute';
	item.parentNode.appendChild(eT);
	eT.style.left = fP + 'px';
	eT.style.top = fT + 'px';
	eT.style.height = 1 + 'px';
	eT.style.width = 1 + 'px';
	eT.style.clip = gC;
	eT.style.backgroundColor = 'Transparent';
	eT.style.padding = 0;
	eT.style.margin = 0;
	item.style.left = 0 + 'px';
	item.style.top = 0 + 'px';
	eT.appendChild(item);
	log(gC);
	if (show) {
		switch (direction * 1) {
		case 2: {
			item.style.top = (item.offsetTop - item.offsetHeight) + 'px';
			eI = 0;
			break;
		}
		case 6: {
			item.style.left = (item.offsetLeft - item.offsetWidth) + 'px';
			vstep = 0;
			break;
		}
		case 8: {
			item.style.top = (item.offsetTop + item.offsetHeight) + 'px';
			eI = 0;
			break;
		}
		case 4: {
			item.style.left = (item.offsetLeft + item.offsetWidth) + 'px';
			vstep = 0;
			break;
		}
		}
	} else {
		switch (direction) {
		case 2: {
			item.gS = (item.offsetTop + item.offsetHeight);
			eI = 0;
			break;
		}
		case 6: {
			item.gQ = (item.offsetLeft + item.offsetWidth);
			vstep = 0;
			break;
		}
		case 8: {
			item.gS = (item.offsetTop - item.offsetHeight);
			eI = 0;
			break;
		}
		case 4: {
			item.gQ = (item.offsetLeft - item.offsetWidth);
			vstep = 0;
			break;
		}
		}
	}
	log('item init top is ' + item.style.top);
	item.style.visibility = 'visible';
	var cr = 0;
	if (!bQ) {
		gW = setInterval(
				function() {
					log('slide tTop is ' + gS);
					log('slide current top ' + item.offsetTop);
					switch (direction * 1) {
					case 2:
					case 6: {
						if (direction == 2) {
							item.vstep = Math
									.ceil(0.2 * (item.gS - item.offsetTop));
							eI = 0;
						} else {
							item.eI = Math
									.ceil(0.2 * (item.gQ - item.offsetLeft));
							vstep = 0;
						}
						log('vstep is ' + vstep);
						item.style.top = (item.offsetTop + item.vstep) + 'px';
						item.style.left = (item.offsetLeft + item.eI) + 'px';
						if ((item.offsetTop >= item.gS)
								&& (item.offsetLeft >= item.gQ)) {
							log('slide move over oLeft is ' + fP + ' oTop is '
									+ fT);
							eT.parentNode.replaceChild(item, eT);
							item.style.top = item.fT + 'px';
							item.style.left = item.fP + 'px';
							if (!show) {
								item.style.visibility = 'hidden'
							}
							clearTimeout(gW);
							log('item final top is ' + item.offsetTop);
							return;
						}
						break;
					}
					case 8:
					case 4: {
						vstep = Math.ceil(0.2 * Math.abs(item.offsetTop - gS));
						eI = Math.ceil(0.2 * Math.abs(item.offsetLeft - gQ));
						item.style.top = (item.offsetTop - vstep) + 'px';
						item.style.left = (item.offsetLeft - eI) + 'px';
						if ((item.offsetTop <= gS) && (item.offsetLeft <= gQ)) {
							log('slide move over oLeft is ' + fP + ' oTop is '
									+ fT);
							eT.parentNode.replaceChild(item, eT);
							item.style.top = fT + 'px';
							item.style.left = fP + 'px';
							if (!show) {
								item.style.visibility = 'hidden'
							}
							clearTimeout(gW);
							log('item final top is ' + item.offsetTop);
							return;
						}
						break;
					}
					}
				}, gR);
	}
};
function aL(gH, fD, fE) {
	cu = false;
	if (!gH.eQ)
		gH.eQ = 0;
	if (!gH.eP)
		gH.eP = 0;
	eQ = gH.eQ;
	eP = gH.eP;
	if (document.documentElement && document.documentElement.scrollTop) {
		dr = document.documentElement.scrollTop
	} else if (document.body) {
		dr = document.body.scrollTop
	}
	if (document.documentElement && document.documentElement.scrollLeft) {
		dq = document.documentElement.scrollLeft
	} else if (document.body) {
		dq = document.body.scrollLeft
	}
	if (fE) {
		if (dr != eQ) {
			gg = .1 * (dr - eQ);
			if (gg > 0) {
				gg = Math.ceil(gg)
			} else {
				gg = Math.floor(gg);
			}
			gH.style.top = gH.offsetTop + gg + 'px';
			eQ = eQ + gg;
			cu = true;
		}
	}
	if (fD) {
		if (dq != eP) {
			gg = .1 * (dq - eP);
			if (gg > 0) {
				gg = Math.ceil(gg);
			} else {
				gg = Math.floor(gg);
			}
			gH.style.left = gH.offsetLeft + gg + 'px';
			eP = eP + gg;
			cu = true;
		}
	}
	gH.eQ = eQ;
	gH.eP = eP;
};
function L() {
	cf = !cf;
}