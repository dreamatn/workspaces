package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5848 {
    DBParm DDTVCH_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_CUS_AC = " ";
    short WS_PAGE = 0;
    short WS_LINE = 0;
    DDOT5848_WS_DZB_DATA WS_DZB_DATA = new DDOT5848_WS_DZB_DATA();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRVCH DDRVCH = new DDRVCH();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRL CICQACRL = new CICQACRL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCPBKS DDCPBKS = new DDCPBKS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    DDB5848_AWA_5848 DDB5848_AWA_5848;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT5848 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5848_AWA_5848>");
        DDB5848_AWA_5848 = (DDB5848_AWA_5848) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_GET_ACTYP_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5848_AWA_5848.CARD_NO);
        if (DDB5848_AWA_5848.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_GET_ACTYP_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDB5848_AWA_5848.CARD_NO;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            B210_INQ_DC_VCH();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_INQ_DC_VCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DC");
        CEP.TRC(SCCGWA, DDB5848_AWA_5848.CARD_NO);
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DDB5848_AWA_5848.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        R000_01_GET_DZB_AC_PROC();
        if (pgmRtn) return;
        R000_READ_DDTVCH();
        if (pgmRtn) return;
        if (CICQACRL.RC.RC_CODE == 0) {
            R000_GET_DZB_PRT_PAGE();
            if (pgmRtn) return;
        }
        R000_04_OUT_DC_DATA();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_01_GET_DZB_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDB5848_AWA_5848.CARD_NO;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            WS_CUS_AC = CICQACRL.O_DATA.O_AC_NO;
        } else {
            CEP.TRC(SCCGWA, "DZB NOT FND");
        }
    }
    public void R000_READ_DDTVCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CUS_AC);
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = WS_CUS_AC;
        DDRVCH.VCH_TYPE = '2';
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC "
            + "AND VCH_TYPE = '2'";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "DDTVCH NOT FND");
        }
    }
    public void R000_GET_DZB_PRT_PAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPBKS);
        DDCPBKS.KEY.TYP = "PDD01";
        DDCPBKS.KEY.CD = "PASSBOOK";
        BPCPRMR.DAT_PTR = DDCPBKS;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_PAGE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.PAGE_LINE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_LINE);
        CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
        if (DDRVCH.PRT_LINE > 0) {
            WS_LINE = (short) (DDRVCH.PRT_LINE % DDCPBKS.DATA_TXT.PAGE_LINE);
            WS_PAGE = (short) ((DDRVCH.PRT_LINE - WS_LINE) / DDCPBKS.DATA_TXT.PAGE_LINE);
            WS_PAGE += 1;
        }
        CEP.TRC(SCCGWA, WS_PAGE);
        CEP.TRC(SCCGWA, WS_LINE);
    }
    public void R000_04_OUT_DC_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUTPUT DC VCH");
        IBS.init(SCCGWA, WS_DZB_DATA);
        WS_DZB_DATA.WS_AC_TYP = "A1";
        WS_DZB_DATA.WS_ENTY_TYP = CICQACAC.O_DATA.O_ACR_DATA.O_ENTY_TYP;
        WS_DZB_DATA.WS_AC_NO = DDB5848_AWA_5848.CARD_NO;
        WS_DZB_DATA.WS_OPEN_DT = CICQACAC.O_DATA.O_ACR_DATA.O_OPEN_DT_ACR;
        WS_DZB_DATA.WS_OPEN_BR = CICQACAC.O_DATA.O_ACR_DATA.O_OPN_BR_ACR;
        WS_DZB_DATA.WS_FRM_APP = CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR;
        WS_DZB_DATA.WS_AC_CNM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        WS_DZB_DATA.WS_AC_ENM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM;
        if (DCCUCINF.CARD_MEDI == '1') {
            WS_DZB_DATA.WS_VCH_TYPE = 'A';
        } else if (DCCUCINF.CARD_MEDI == '2') {
            WS_DZB_DATA.WS_VCH_TYPE = 'B';
        } else if (DCCUCINF.CARD_MEDI == '3') {
            WS_DZB_DATA.WS_VCH_TYPE = 'C';
        } else {
        }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_DZB_DATA.WS_VCH_STS = '2';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_DZB_DATA.WS_VCH_STS = '3';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_DZB_DATA.WS_VCH_STS = '6';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_DZB_DATA.WS_VCH_STS = '7';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_DZB_DATA.WS_VCH_STS = '8';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            WS_DZB_DATA.WS_VCH_STS = '9';
        } else {
            WS_DZB_DATA.WS_VCH_STS = '1';
        }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_DZB_DATA.WS_PSW_STS = '3';
        } else {
            WS_DZB_DATA.WS_PSW_STS = '1';
        }
        WS_DZB_DATA.WS_VCH_BVNO = DDRVCH.PSBK_NO;
        WS_DZB_DATA.WS_VCH_SEQ = DDRVCH.PSBK_SEQ;
        WS_DZB_DATA.WS_VCH_PROD_CD = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
        WS_DZB_DATA.WS_PRT_PAGE = WS_PAGE;
        WS_DZB_DATA.WS_PRT_LINE = DDRVCH.PRT_LINE;
        WS_DZB_DATA.WS_UPT_CNT = DDRVCH.UPT_CNT;
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_AC_TYP);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_ENTY_TYP);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_AC_NO);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_OPEN_DT);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_OPEN_BR);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_AC_CNM);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_AC_ENM);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_VCH_TYPE);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_VCH_STS);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_PSW_STS);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_VCH_BVID);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_VCH_BVNO);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_VCH_SEQ);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_VCH_PROD_CD);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_PRT_PAGE);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_PRT_LINE);
        CEP.TRC(SCCGWA, WS_DZB_DATA.WS_UPT_CNT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_DZB_DATA);
        SCCMPAG.DATA_LEN = 658;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF, true);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
