package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFPARM {
    String JIBS_tmp_str[] = new String[10];
    String CPN_R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String CPN_R_FEE_BPZRMBPM = "BP-R-MBRW-PARM      ";
    String K_PGM_NAME = "BPZFPARM";
    String K_PARM_FBAS = "FBAS ";
    String K_PARM_FCOM = "FCOM ";
    String K_PARM_FSTD = "FSTD ";
    String K_PARM_FFAV = "FFAV ";
    String K_PARM_FAMO = "FAMO ";
    String K_PARM_FREB = "FREB ";
    String K_PARM_FPRD = "FPRD ";
    String K_PARM_FCPN = "FCPN ";
    String K_PARM_FSVR = "FSVR ";
    String K_PARM_FMSK = "FMSK ";
    String K_PARM_FEXP = "FEXP ";
    String K_PARM_FAGR = "FAGR ";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String K_HIS_REMARKS = "FEE DETAILS INFO ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPZFPARM_WS_HIS_BPVPARM WS_HIS_BPVPARM = new BPZFPARM_WS_HIS_BPVPARM();
    String WS_HIS_COPYBOOK_NAME = " ";
    String WS_DESC = " ";
    String WS_CDESC = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPVFBAS BPVOHBAS = new BPVFBAS();
    BPVFBAS BPVNHBAS = new BPVFBAS();
    BPVFCOM BPVOHCOM = new BPVFCOM();
    BPVFCOM BPVNHCOM = new BPVFCOM();
    BPVFSTD BPVOHSTD = new BPVFSTD();
    BPVFSTD BPVNHSTD = new BPVFSTD();
    BPVFFAV BPVOHFAV = new BPVFFAV();
    BPVFFAV BPVNHFAV = new BPVFFAV();
    BPVFAMO BPVOHAMO = new BPVFAMO();
    BPVFAMO BPVNHAMO = new BPVFAMO();
    BPVFREB BPVOHREB = new BPVFREB();
    BPVFREB BPVNHREB = new BPVFREB();
    BPVFPRD BPVOHPRD = new BPVFPRD();
    BPVFPRD BPVNHPRD = new BPVFPRD();
    BPVFCPN BPVOHCPN = new BPVFCPN();
    BPVFCPN BPVNHCPN = new BPVFCPN();
    BPVFSVR BPVOHSVR = new BPVFSVR();
    BPVFSVR BPVNHSVR = new BPVFSVR();
    BPVFMSK BPVOHMSK = new BPVFMSK();
    BPVFMSK BPVNHMSK = new BPVFMSK();
    BPVFEXP BPVOHEXP = new BPVFEXP();
    BPVFEXP BPVNHEXP = new BPVFEXP();
    BPVFAGR BPVOHAGR = new BPVFAGR();
    BPVFAGR BPVNHAGR = new BPVFAGR();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRMBPM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCTFSTD BPCTFSTD = new BPCTFSTD();
    BPCTFSVR BPCTFSVR = new BPCTFSVR();
    BPCTFFAV BPCTFFAV = new BPCTFFAV();
    BPCTFCOM BPCTFCOM = new BPCTFCOM();
    SCCGWA SCCGWA;
    BPCFPARM BPCFPARM;
    BPVFCOM BPVFBAS;
    BPVFCOM BPVOPBAS;
    BPVFCOM BPVFCOM;
    BPVFCOM BPVOPCOM;
    BPVFCOM BPVFSTD;
    BPVFCOM BPVOPSTD;
    BPVFCOM BPVFFAV;
    BPVFCOM BPVOPFAV;
    BPVFCOM BPVFAMO;
    BPVFCOM BPVOPAMO;
    BPVFCOM BPVFREB;
    BPVFCOM BPVOPREB;
    BPVFCOM BPVFPRD;
    BPVFCOM BPVOPPRD;
    BPVFCOM BPVFCPN;
    BPVFCOM BPVOPCPN;
    BPVFCOM BPVFSVR;
    BPVFCOM BPVOPSVR;
    BPVFCOM BPVFMSK;
    BPVFCOM BPVOPMSK;
    BPVFCOM BPVFEXP;
    BPVFCOM BPVOPEXP;
    BPVFCOM BPVFAGR;
    BPVFCOM BPVOPAGR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFPARM BPCFPARM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFPARM = BPCFPARM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFPARM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFPARM.INFO.TYPE);
        if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FBAS)) {
            BPVFBAS = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCOM)) {
            BPVFCOM = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSTD)) {
            BPVFSTD = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FFAV)) {
            BPVFFAV = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAMO)) {
            BPVFAMO = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FREB)) {
            BPVFREB = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FPRD)) {
            BPVFPRD = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCPN)) {
            BPVFCPN = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSVR)) {
            BPVFSVR = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FMSK)) {
            BPVFMSK = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FEXP)) {
            BPVFEXP = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAGR)) {
            BPVFAGR = (BPVFCOM) BPCFPARM.INFO.POINTER;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FARM_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_PRE_PARM_DATA();
    }
    public void R000_FPARM_PROC() throws IOException,SQLException,Exception {
        if (BPCFPARM.INFO.FUNC == '5') {
            BPRMBPM.KEY.TYPE = BPRPRMT.KEY.TYP;
            BPRMBPM.KEY.CODE = BPRPRMT.KEY.CD;
            BPRMBPM.EFF_DATE = WS_EFF_DT;
            BPCRMBPM.PTR = BPRMBPM;
            CEP.TRC(SCCGWA, " PRMB-DAT-PTR ");
        } else {
            BPCPRMM.DAT_PTR = BPRPRMT;
        }
        CEP.TRC(SCCGWA, BPCFPARM.INFO.FUNC);
        if (BPCFPARM.INFO.FUNC == '0') {
            BPCPRMM.FUNC = '0';
            S000_CALL_BPZPRMM();
        } else if (BPCFPARM.INFO.FUNC == '1') {
            BPCPRMM.FUNC = '2';
            S000_CALL_BPZPRMM();
        } else if (BPCFPARM.INFO.FUNC == '3') {
            BPCPRMM.FUNC = '3';
            S000_CALL_BPZPRMM();
        } else if (BPCFPARM.INFO.FUNC == '4') {
            BPCPRMM.FUNC = '4';
            S000_CALL_BPZPRMM();
        } else if (BPCFPARM.INFO.FUNC == '2') {
            BPCPRMM.FUNC = '1';
            S000_CALL_BPZPRMM();
        } else if (BPCFPARM.INFO.FUNC == '5') {
            if (BPCFPARM.INFO.OPT == '0') {
                BPCRMBPM.FUNC = 'S';
            } else {
                if (BPCFPARM.INFO.OPT == '1') {
                    BPCRMBPM.FUNC = 'R';
                } else {
                    if (BPCFPARM.INFO.OPT == '2') {
                        BPCRMBPM.FUNC = 'E';
                    } else {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            S000_CALL_BPZRMBPM();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCFPARM.INFO.FUNC != '5' 
            || (BPCFPARM.RETURN_INFO != 'N' 
            && BPCFPARM.INFO.OPT == '1')) {
            B020_SET_PARM_VAL();
        }
        B030_HISTORY_RECORD();
    }
    public void B010_PRE_PARM_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPRMBPM);
        CEP.TRC(SCCGWA, BPCFPARM.INFO.TYPE);
        if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FBAS)) {
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCOM)) {
            IBS.init(SCCGWA, BPCTFCOM);
            BPCTFCOM.INFO.FUNC = BPCFPARM.INFO.FUNC;
            BPCTFCOM.INFO.OPT = BPCFPARM.INFO.OPT;
            BPCTFCOM.INFO.REC_LEN = 169;
            BPCTFCOM.INFO.POINTER = BPCFPARM.INFO.POINTER;
            S000_CALL_BPZTFCOM();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSTD)) {
            IBS.init(SCCGWA, BPCTFSTD);
            BPCTFSTD.INFO.FUNC = BPCFPARM.INFO.FUNC;
            BPCTFSTD.INFO.OPT = BPCFPARM.INFO.OPT;
            BPCTFSTD.INFO.REC_LEN = 693;
            BPCTFSTD.INFO.POINTER = BPCFPARM.INFO.POINTER;
            S000_CALL_BPZTFSTD();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FFAV)) {
            IBS.init(SCCGWA, BPCTFFAV);
            BPCTFFAV.INFO.FUNC = BPCFPARM.INFO.FUNC;
            BPCTFFAV.INFO.OPT = BPCFPARM.INFO.OPT;
            BPCTFFAV.INFO.REC_LEN = 1367;
            BPCTFFAV.INFO.POINTER = BPCFPARM.INFO.POINTER;
            S000_CALL_BPZTFFAV();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAMO)) {
            BPRPRMT.KEY.TYP = K_PARM_FAMO;
            CEP.TRC(SCCGWA, BPVFAMO.KEY);
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPVFAMO.KEY);
            CEP.TRC(SCCGWA, WS_DESC);
            BPRPRMT.DESC = WS_DESC;
            CEP.TRC(SCCGWA, WS_CDESC);
            BPRPRMT.CDESC = WS_CDESC;
            CEP.TRC(SCCGWA, BPVFAMO.DATE.EFF_DATE);
            WS_EFF_DT = BPVFAMO.DATE.EFF_DATE;
            CEP.TRC(SCCGWA, BPVFAMO.DATE.EXP_DATE);
            WS_EXP_DT = BPVFAMO.DATE.EXP_DATE;
            CEP.TRC(SCCGWA, BPVFAMO.KEY);
            BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPVFAMO);
            R000_FPARM_PROC();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FREB)) {
            BPRPRMT.KEY.TYP = K_PARM_FREB;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPVFREB.KEY);
            BPRPRMT.DESC = WS_DESC;
            BPRPRMT.CDESC = WS_CDESC;
            WS_EFF_DT = BPVFREB.VAL.EFF_DATE;
            WS_EXP_DT = BPVFREB.VAL.EXP_DATE;
            BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPVFREB);
            R000_FPARM_PROC();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FPRD)) {
            CEP.TRC(SCCGWA, BPVFPRD.KEY);
            BPRPRMT.KEY.TYP = K_PARM_FPRD;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPVFPRD.KEY);
            BPRPRMT.DESC = WS_DESC;
            BPRPRMT.CDESC = WS_CDESC;
            WS_EFF_DT = BPVFPRD.DATE.EFF_DATE;
            WS_EXP_DT = BPVFPRD.DATE.EXP_DATE;
            BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPVFPRD);
            R000_FPARM_PROC();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCPN)) {
            BPRPRMT.KEY.TYP = K_PARM_FCPN;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPVFCPN.KEY);
            BPRPRMT.DESC = WS_DESC;
            BPRPRMT.CDESC = WS_CDESC;
            WS_EFF_DT = BPVFCPN.VAL.EFF_DATE;
            WS_EXP_DT = BPVFCPN.VAL.EXP_DATE;
            BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPVFCPN);
            R000_FPARM_PROC();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSVR)) {
            IBS.init(SCCGWA, BPCTFSVR);
            BPCTFSVR.INFO.FUNC = BPCFPARM.INFO.FUNC;
            BPCTFSVR.INFO.OPT = BPCFPARM.INFO.OPT;
            BPCTFSVR.INFO.REC_LEN = 1978;
            BPCTFSVR.INFO.POINTER = BPCFPARM.INFO.POINTER;
            S000_CALL_BPZTFSVR();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FMSK)) {
            BPRPRMT.KEY.TYP = K_PARM_FMSK;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPVFMSK.KEY);
            BPRPRMT.DESC = WS_DESC;
            BPRPRMT.CDESC = WS_CDESC;
            WS_EFF_DT = BPVFMSK.VAL.EFF_DT;
            WS_EXP_DT = BPVFMSK.VAL.EXP_DT;
            BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPVFMSK);
            R000_FPARM_PROC();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FEXP)) {
            BPRPRMT.KEY.TYP = K_PARM_FEXP;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPVFEXP.KEY);
            BPRPRMT.DESC = WS_DESC;
            BPRPRMT.CDESC = WS_CDESC;
            WS_EFF_DT = BPVFEXP.VAL.EFF_DATE;
            WS_EXP_DT = BPVFEXP.VAL.EXP_DATE;
            BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPVFEXP);
            CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
            R000_FPARM_PROC();
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAGR)) {
            BPRPRMT.KEY.TYP = K_PARM_FAGR;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPVFAGR.KEY);
            BPRPRMT.DESC = WS_DESC;
            BPRPRMT.CDESC = WS_CDESC;
            WS_EFF_DT = BPVFAGR.DATE.EFF_DATE;
            WS_EXP_DT = BPVFAGR.DATE.EXP_DATE;
            BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPVFAGR);
            R000_FPARM_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FARM_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_SET_PARM_VAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFPARM.INFO.TYPE);
        if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FBAS)) {
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCOM)) {
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT.FILLER);
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFCOM);
            CEP.TRC(SCCGWA, BPVFCOM);
            CEP.TRC(SCCGWA, BPVFCOM.EFF_DATE);
            BPVFCOM.EFF_DATE = BPCPRMM.EFF_DT;
            CEP.TRC(SCCGWA, BPVFCOM.EFF_DATE);
            BPVFCOM.EXP_DATE = BPCPRMM.EXP_DT;
            CEP.TRC(SCCGWA, BPVFCOM.EXP_DATE);
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSTD)) {
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT.FILLER);
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFSTD);
            BPVFSTD.DATE.EFF_DATE = BPCPRMM.EFF_DT;
            BPVFSTD.DATE.EXP_DATE = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FFAV)) {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFFAV);
            BPVFFAV.DATE.EFF_DATE = BPCPRMM.EFF_DT;
            BPVFFAV.DATE.EXP_DATE = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAMO)) {
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT.FILLER);
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFAMO);
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            BPVFAMO.DATE.EFF_DATE = BPCPRMM.EFF_DT;
            CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
            BPVFAMO.DATE.EXP_DATE = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FREB)) {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFREB);
            BPVFREB.VAL.EFF_DATE = BPCPRMM.EFF_DT;
            BPVFREB.VAL.EXP_DATE = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FPRD)) {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFPRD);
            BPVFPRD.DATE.EFF_DATE = BPCPRMM.EFF_DT;
            BPVFPRD.DATE.EXP_DATE = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCPN)) {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFCPN);
            BPVFCPN.VAL.EFF_DATE = BPCPRMM.EFF_DT;
            BPVFCPN.VAL.EXP_DATE = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSVR)) {
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT.FILLER);
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFSVR);
            CEP.TRC(SCCGWA, BPVFSVR);
            BPVFSVR.VAL.EFF_DATE = BPCPRMM.EFF_DT;
            BPVFSVR.VAL.EXP_DATE = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FMSK)) {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFMSK);
            BPVFMSK.VAL.EFF_DT = BPCPRMM.EFF_DT;
            BPVFMSK.VAL.EXP_DT = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FEXP)) {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFEXP);
            BPVFEXP.VAL.EFF_DATE = BPCPRMM.EFF_DT;
            BPVFEXP.VAL.EXP_DATE = BPCPRMM.EXP_DT;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAGR)) {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPVFAGR);
            BPVFAGR.DATE.EFF_DATE = BPCPRMM.EFF_DT;
            BPVFAGR.DATE.EXP_DATE = BPCPRMM.EXP_DT;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FARM_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCFPARM.INFO.FUNC == '5') {
            if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FBAS)) {
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCOM)) {
                CEP.TRC(SCCGWA, BPVFCOM);
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFCOM);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSTD)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFSTD);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FFAV)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFFAV);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAMO)) {
                CEP.TRC(SCCGWA, BPVFAMO);
                CEP.TRC(SCCGWA, BPRMBPM.BLOB_VAL);
                CEP.TRC(SCCGWA, BPRMBPM.BLOB_VAL.trim().length());
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFAMO);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FREB)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFREB);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FPRD)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFPRD);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCPN)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFCPN);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSVR)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFSVR);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FMSK)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFMSK);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FEXP)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFEXP);
            } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAGR)) {
                IBS.CPY2CLS(SCCGWA, BPRMBPM.BLOB_VAL, BPVFAGR);
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FARM_TYP_ERR;
                S000_ERR_MSG_PROC();
            }
            BPCRMBPM.PTR = BPRPRMT;
        } else {
            BPCPRMM.DAT_PTR = BPRPRMT;
        }
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        if (BPCFPARM.INFO.TYPE.equalsIgnoreCase("FMSK) BPCFPARM.INFO.TYPE.equalsIgnoreCase(") 
            || BPCFPARM.INFO.TYPE.equalsIgnoreCase("FSVR) BPCFPARM.INFO.TYPE.equalsIgnoreCase(") 
            || BPCFPARM.INFO.TYPE.equalsIgnoreCase("FFAV) BPCFPARM.INFO.TYPE.equalsIgnoreCase(") 
            || BPCFPARM.INFO.TYPE.equalsIgnoreCase("FPRD) BPCFPARM.INFO.TYPE.equalsIgnoreCase(") 
            || BPCFPARM.INFO.TYPE.equalsIgnoreCase("FAGR) BPCFPARM.INFO.TYPE.equalsIgnoreCase(")) {
        } else {
            if (BPCFPARM.RETURN_INFO == 'F') {
                if (BPCFPARM.INFO.FUNC == '0' 
                    || BPCFPARM.INFO.FUNC == '2') {
                    S020_01_HISTORY_RECORD();
                } else {
                    if (BPCFPARM.INFO.FUNC == '1') {
                        S030_01_HISTORY_RECORD();
                    }
                }
            }
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_EFF_DT);
        CEP.TRC(SCCGWA, WS_EXP_DT);
        BPCPRMM.EFF_DT = WS_EFF_DT;
        BPCPRMM.EXP_DT = WS_EXP_DT;
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_BPZPRMM, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC.RC_RTNCODE);
        T000_CHECK_RETURNCODE();
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRMBPM.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRMBPM.KEY.CODE);
        CEP.TRC(SCCGWA, BPCRMBPM.FUNC);
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_BPZRMBPM, BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        T000_CHECK_RETURNCODE_BROWSE();
    }
    public void S000_CALL_BPZTFSTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-STD-INFO", BPCTFSTD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTFSTD.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPARM.RC);
        BPCFPARM.RETURN_INFO = BPCTFSTD.RETURN_INFO;
    }
    public void S000_CALL_BPZTFCOM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-COM-INFO", BPCTFCOM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTFCOM.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPARM.RC);
        BPCFPARM.RETURN_INFO = BPCTFCOM.RETURN_INFO;
    }
    public void S000_CALL_BPZTFFAV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-FAV-INFO", BPCTFFAV);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTFFAV.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPARM.RC);
        BPCFPARM.RETURN_INFO = BPCTFFAV.RETURN_INFO;
    }
    public void S000_CALL_BPZTFSVR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-SVR-INFO", BPCTFSVR);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTFSVR.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPARM.RC);
        BPCFPARM.RETURN_INFO = BPCTFSVR.RETURN_INFO;
    }
    public void S020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCFPARM.INFO.FUNC == '0') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        WS_HIS_BPVPARM.WS_HIS_BPV = "BPV";
        WS_HIS_BPVPARM.WS_HIS_PARM = BPCFPARM.INFO.TYPE;
        WS_HIS_COPYBOOK_NAME = IBS.CLS2CPY(SCCGWA, WS_HIS_BPVPARM);
        BPCPNHIS.INFO.FMT_ID = WS_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void S030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        WS_HIS_BPVPARM.WS_HIS_BPV = "BPV";
        WS_HIS_BPVPARM.WS_HIS_PARM = BPCFPARM.INFO.TYPE;
        WS_HIS_COPYBOOK_NAME = IBS.CLS2CPY(SCCGWA, WS_HIS_BPVPARM);
        CEP.TRC(SCCGWA, WS_HIS_COPYBOOK_NAME);
        BPCPNHIS.INFO.FMT_ID = WS_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        CEP.TRC(SCCGWA, BPCFPARM.INFO.TYPE);
        if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FBAS)) {
            BPVOPBAS = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPBAS);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHBAS);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFBAS);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHBAS);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHBAS;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHBAS;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCOM)) {
            BPVOPCOM = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            IBS.CLONE(SCCGWA, BPVOPCOM, BPVOHCOM);
            IBS.CLONE(SCCGWA, BPVFCOM, BPVNHCOM);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHCOM;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHCOM;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSTD)) {
            BPVOPSTD = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPSTD);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHSTD);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFSTD);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHSTD);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHSTD;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHSTD;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FFAV)) {
            BPVOPFAV = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPFAV);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHFAV);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFFAV);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHFAV);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHFAV;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHFAV;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAMO)) {
            BPVOPAMO = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPAMO);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHFAV);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFAMO);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHFAV);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHFAV;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHFAV;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FREB)) {
            BPVOPREB = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPREB);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHREB);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFREB);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHREB);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHREB;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHREB;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FPRD)) {
            BPVOPPRD = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPPRD);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHPRD);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFPRD);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHPRD);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHPRD;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHPRD;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FCPN)) {
            BPVOPCPN = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPCPN);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHCPN);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFCPN);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHCPN);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHCPN;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHCPN;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FSVR)) {
            BPVOPSVR = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPSVR);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHSVR);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFSVR);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHSVR);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHSVR;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHSVR;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FMSK)) {
            BPVOPMSK = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPMSK);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHMSK);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFMSK);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHMSK);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHMSK;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHMSK;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FEXP)) {
            BPVOPEXP = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPEXP);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHEXP);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFEXP);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHEXP);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHEXP;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHEXP;
        } else if (BPCFPARM.INFO.TYPE.equalsIgnoreCase(K_PARM_FAGR)) {
            BPVOPAGR = (BPVFSTD) BPCFPARM.INFO.POINTER_OLD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVOPAGR);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVOHAGR);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFAGR);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPVNHAGR);
            BPCPNHIS.INFO.OLD_DAT_PT = BPVOHAGR;
            BPCPNHIS.INFO.NEW_DAT_PT = BPVNHAGR;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FARM_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_CHECK_RETURNCODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPARM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            BPCFPARM.RETURN_INFO = 'F';
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                BPCFPARM.RETURN_INFO = 'N';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST)) {
                    BPCFPARM.RETURN_INFO = 'D';
                } else {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void T000_CHECK_RETURNCODE_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRMBPM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RETURN_INFO == 'F') {
            BPCFPARM.RETURN_INFO = 'F';
        } else {
            if (BPCRMBPM.RETURN_INFO == 'L' 
                || BPCRMBPM.RETURN_INFO == 'N') {
                BPCFPARM.RETURN_INFO = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFPARM.RC.RC_CODE != 0) {
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
