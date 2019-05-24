package com.hisun.TD;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPBR36 {
    int JIBS_tmp_int;
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    DBParm TDTBVT_RD;
    brParm TDTSMST_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_TBL_ORGE = "BPTORGI ";
    char WS_SMST_FLG = ' ';
    TDZPBR36_WS_PARM_DATA1 WS_PARM_DATA1 = new TDZPBR36_WS_PARM_DATA1();
    TDZPBR36_WS_PARM_DATA2 WS_PARM_DATA2 = new TDZPBR36_WS_PARM_DATA2();
    TDZPBR36_WS_PARM_DATA3 WS_PARM_DATA3 = new TDZPBR36_WS_PARM_DATA3();
    TDZPBR36_WS_PARM_DATA4 WS_PARM_DATA4 = new TDZPBR36_WS_PARM_DATA4();
    TDZPBR36_WS_PARM_DATA5 WS_PARM_DATA5 = new TDZPBR36_WS_PARM_DATA5();
    int WS_BRAN_BR_NEW = 0;
    int WS_BRAN_BR_OLD = 0;
    int WS_I = 0;
    String WS_VIL_TYP_NEW = " ";
    String WS_VIL_TYP_OLD = " ";
    String WS_AREA_CD_NEW = " ";
    String WS_AREA_CD_OLD = " ";
    char WS_ATTR_OLD = ' ';
    char WS_ATTR_NEW = ' ';
    int WS_SUPR_BR_OLD = 0;
    int WS_SUPR_BR_NEW = 0;
    String WS_FX_BUSI = " ";
    String WS_ACO_AC = " ";
    String WS_ACO_AC_T = " ";
    String WS_CCY = " ";
    int WS_OPN_BR = 0;
    int WS_BR = 0;
    String WS_MSGID = " ";
    char WS_ORGI_FLG = ' ';
    char WS_FLG = ' ';
    char WS_ORGE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRORGI BPRORGI = new BPRORGI();
    BPRORGL BPRORGL = new BPRORGL();
    BPRORGE BPRORGE = new BPRORGE();
    CICQACRI CICQACRI = new CICQACRI();
    SCCFMT SCCFMT = new SCCFMT();
    BPCRORGI BPCRORGI = new BPCRORGI();
    BPCOORGI BPCOORGI = new BPCOORGI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    DDCUQCAC DDCUQCAC = new DDCUQCAC();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    CICACCU CICACCU = new CICACCU();
    BPCRORGL BPCRORGL = new BPCRORGL();
    CICQACAC CICQACAC = new CICQACAC();
    TDRBVT TDRBVT = new TDRBVT();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICCUST CICCUST = new CICCUST();
    BPCAMMDP BPCAMMDP = new BPCAMMDP();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    CICSACRL CICSACRL = new CICSACRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    TDCPBR36 TDCPBR36;
    public void MP(SCCGWA SCCGWA, TDCPBR36 TDCPBR36) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPBR36 = TDCPBR36;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZPBR36 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, TDCPBR36.AC_NO);
        CEP.TRC(SCCGWA, TDCPBR36.ACO_AC);
        CEP.TRC(SCCGWA, TDCPBR36.TYP);
        CEP.TRC(SCCGWA, TDCPBR36.OLD_BR);
        CEP.TRC(SCCGWA, TDCPBR36.NEW_BR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDCPBR36.TYP == '1') {
            B010_UPDATE_ACNO();
            if (pgmRtn) return;
        } else if (TDCPBR36.TYP == '2') {
            B020_UPDATE_ACOAC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_UPDATE_ACNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        IBS.init(SCCGWA, TDRSMST);
        TDRCMST.KEY.AC_NO = TDCPBR36.AC_NO;
        TDRSMST.AC_NO = TDCPBR36.AC_NO;
        B110_UPDATE_CMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BR);
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BK);
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'M';
        CICSACR.DATA.AGR_NO = TDCPBR36.AC_NO;
        CICSACR.DATA.OPN_BR = TDRCMST.OWNER_BR;
        CICSACR.DATA.OWNER_BK = TDRCMST.OWNER_BK;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B110_UPDATE_CMST() throws IOException,SQLException,Exception {
        T000_READU_CMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BR);
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BK);
        CEP.TRC(SCCGWA, TDRCMST.CHE_BR);
        TDRCMST.OWNER_BR = TDCPBR36.NEW_BR;
        TDRCMST.CHE_BR = TDCPBR36.NEW_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = TDCPBR36.NEW_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        WS_BR = TDRCMST.OWNER_BRDP;
        TDRCMST.OWNER_BRDP = BPCPQORG.BBR;
        TDRCMST.OWNER_BK = BPCPQORG.BRANCH_BR;
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_CMST();
        if (pgmRtn) return;
    }
    public void B210_UPDATE_SMST() throws IOException,SQLException,Exception {
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLG != 'N') {
            CEP.TRC(SCCGWA, TDRSMST.OWNER_BR);
            CEP.TRC(SCCGWA, TDRSMST.OWNER_BRDP);
            CEP.TRC(SCCGWA, TDRSMST.OWNER_BK);
            CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
            TDRSMST.OWNER_BR = TDCPBR36.NEW_BR;
            TDRSMST.CHE_BR = TDCPBR36.NEW_BR;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = TDCPBR36.NEW_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.BBR);
            CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
            CEP.TRC(SCCGWA, TDRSMST.OWNER_BRDP);
            CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
            WS_BR = TDRSMST.OWNER_BRDP;
            TDRSMST.OWNER_BRDP = BPCPQORG.BBR;
            TDRSMST.OWNER_BK = BPCPQORG.BRANCH_BR;
            T000_REWRITE_SMST();
            if (pgmRtn) return;
            if (BPCPQORG.BBR != WS_BR) {
                B260_WRT_VCH_EVENT();
                if (pgmRtn) return;
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
    }
    public void B020_UPDATE_ACOAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDCPBR36.ACO_AC;
        T000_READU_SMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BR);
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BK);
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        TDRSMST.OWNER_BR = TDCPBR36.NEW_BR;
        TDRSMST.CHE_BR = TDCPBR36.NEW_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = TDCPBR36.NEW_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BRDP);
        WS_BR = TDRSMST.OWNER_BRDP;
        TDRSMST.OWNER_BRDP = BPCPQORG.BBR;
        TDRSMST.OWNER_BK = BPCPQORG.SUPR_BR;
        CEP.TRC(SCCGWA, "222");
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BR);
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BK);
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        T000_REWRITE_SMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        R000_GET_SALE_PROD_INFO();
        if (pgmRtn) return;
        if (BPCPQORG.BBR != WS_BR 
            && TDRSMST.ACO_STS == '0') {
            B260_WRT_VCH_EVENT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
        CICSACAC.DATA.OPN_BR = TDRSMST.OWNER_BR;
        CICSACAC.DATA.OWNER_BK = TDRSMST.OWNER_BK;
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_NO);
        CEP.TRC(SCCGWA, CICSACAC.DATA.OPN_BR);
        CEP.TRC(SCCGWA, CICSACAC.DATA.OWNER_BK);
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void R000_GET_SALE_PROD_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
    }
    public void B260_WRT_VCH_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1")) {
            BPCPOEWA.DATA.CNTR_TYPE = "CAAC";
        } else {
            BPCPOEWA.DATA.CNTR_TYPE = "MMDP";
        }
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
            || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            BPCPOEWA.DATA.CNTR_TYPE = "MMDP";
        }
        BPCPOEWA.DATA.PROD_CODE = TDRSMST.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "BC";
        BPCPOEWA.DATA.BR_OLD = TDCPBR36.OLD_BR;
        BPCPOEWA.DATA.BR_NEW = TDCPBR36.NEW_BR;
        BPCPOEWA.DATA.PAY_BR = TDCPBR36.NEW_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDRSMST.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = TDRSMST.BAL;
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = TDRSMST.BUD_INT;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, "=========");
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDRSMST.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO = TDRSMST.KEY.ACO_AC;
        if (TDRSMST.OPEN_DR_AC.trim().length() > 0) {
            BPCPOEWA.DATA.AC_NO_REL = TDRSMST.OPEN_DR_AC;
            BPCPOEWA.DATA.THEIR_AC = TDRSMST.OPEN_DR_AC;
        }
        if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[2-1].AMT == 0) {
        } else {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (!TDRSMST.STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
                S000_CALL_BPZPOEWA();
                if (pgmRtn) return;
            }
        }
        if (TDRSMST.BV_TYP == '3') {
            B270_WRT_VCH_EVENT();
            if (pgmRtn) return;
        }
    }
    public void B270_WRT_VCH_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.PROD_CODE = TDRSMST.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "BC";
        BPCPOEWA.DATA.BR_OLD = TDCPBR36.OLD_BR;
        BPCPOEWA.DATA.BR_NEW = TDCPBR36.NEW_BR;
        BPCPOEWA.DATA.PAY_BR = TDCPBR36.NEW_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDRSMST.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = TDRSMST.BAL;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, "=========270");
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO = TDRSMST.KEY.ACO_AC;
        if (TDRSMST.OPEN_DR_AC.trim().length() > 0) {
            BPCPOEWA.DATA.AC_NO_REL = TDRSMST.OPEN_DR_AC;
            BPCPOEWA.DATA.THEIR_AC = TDRSMST.OPEN_DR_AC;
        }
        if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT == 0) {
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void B700_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCAMMDP);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCQCNGL.DAT.INPUT.BR = TDRSMST.OWNER_BR;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        BPCAMMDP.PROD_TYPE = TDRSMST.PROD_CD;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = TDRSMST.AC_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        BPCAMMDP.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        BPCAMMDP.FIN_TYP = CICCUST.O_DATA.O_FIN_TYPE;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAMMDP;
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
    }
    public void B750_WRT_GL_MASTER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.FUNC = 'A';
        BPCUCNGM.KEY.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCUCNGM.PROD_TYPE = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        BPCUCNGM.KEY.AC = TDRSMST.KEY.ACO_AC;
        BPCUCNGM.KEY.REL_SEQ = " ";
        BPCUCNGM.BR = TDRSMST.OWNER_BR;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.DATA[3-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
        BPCUCNGM.DATA[4-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
        BPCUCNGM.DATA[5-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[5-1].MSTNO;
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        CEP.TRC(SCCGWA, CICSACR.RC.RC_CODE);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_REWRITE_CMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READ_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        WS_SMST_FLG = 'N';
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO";
        TDTSMST_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void S000_CALL_BPZRORGI() throws IOException,SQLException,Exception {
        BPCRORGI.INFO.POINTER = BPRORGI;
        BPCRORGI.INFO.LEN = 190;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGI", BPCRORGI);
        if (BPCRORGI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRORGI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZRORGL() throws IOException,SQLException,Exception {
        BPCRORGL.INFO.POINTER = BPRORGL;
        BPCRORGL.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGL", BPCRORGL);
        if (BPCRORGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRORGL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCPBR36.AC_NO;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_SCZBSPS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-S-GET-BSP-INF", SCCBSPS);
    }
    public void S000_CALL_SCZOBSP() throws IOException,SQLException,Exception {
        SCZOBSP SCZOBSP = new SCZOBSP();
        SCZOBSP.MP(SCCGWA, SCCBSP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LINK_BSP_ERR_69);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("CI3011")) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZACCU_SECOND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY, true);
        if (DCCPACTY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZUQCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-CHK-BR-ACTYPE", DDCUQCAC);
        CEP.TRC(SCCGWA, DDCUQCAC.RC);
        if (DDCUQCAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUQCAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-STD-AC", DCCUSPAC);
    }
    public void S000_CALL_DCZUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READU_CMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, "AAAAAAAAAA");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, SCCBINF);
        }
    }
    public void T000_READU_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, "BBBBBBBB");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, SCCBINF);
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
