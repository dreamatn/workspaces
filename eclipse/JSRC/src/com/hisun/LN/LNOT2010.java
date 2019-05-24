package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.GD.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2010 {
    int JIBS_tmp_int;
    DBParm LNTLOAN_RD;
    DBParm LNTCONT_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTPAIP_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTSETL_RD;
    DBParm LNTAGRE_RD;
    DBParm LNTRATL_RD;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "LNC11";
    String PGM_SCSSCLDT = "SCSSCLDT";
    char K_FIX_REPAY = '5';
    char K_FIX_RATE = '0';
    char K_PRE_INT = 'Y';
    String K_CLDD = "CLDD";
    String K_PPMQ_PROD_CLS_ADV = "P015";
    LNOT2010_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT2010_WS_TEMP_VARIABLE();
    char WS_CMMT_RVL_FLG = ' ';
    int WS_AVAIL_START_DATE = 0;
    int WS_AVAIL_END_DATE = 0;
    double WS_AVAIL_AMT = 0;
    double WS_AVAIL_AMT_OLD = 0;
    String WS_PAY_AC = " ";
    String WS_PROD_CD = " ";
    String WS_CONTRACT_NO = " ";
    int WS_BOOK_BR = 0;
    String WS_CTA_CCY = " ";
    double WS_TOT_PRIN_AMT = 0;
    LNOT2010_WS_FLGS WS_FLGS = new LNOT2010_WS_FLGS();
    short WS_TOT_TENORS = 0;
    int WS_CONT_MATU_DATE = 0;
    char WS_REPY_MTH = ' ';
    int WS_FST_END_DT = 0;
    LNOT2010_WS_OUTPUT_DATA WS_OUTPUT_DATA = new LNOT2010_WS_OUTPUT_DATA();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    LNCULNC LNCULNM = new LNCULNC();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    LNRCONT LNRCONT = new LNRCONT();
    LNRSETL LNRSETL = new LNRSETL();
    LNCICRCM LNCICRCM = new LNCICRCM();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    CICACCU CICACCU = new CICACCU();
    LNRRATN LNRRATN = new LNRRATN();
    LNCRRATN LNCRRATN = new LNCRRATN();
    LNRRATL LNRRATL = new LNRRATL();
    LNCRRATL LNCRRATL = new LNCRRATL();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    CICCUST CICCUST = new CICCUST();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    SCCGWA SCCGWA;
    LNB2010_AWA_2010 LNB2010_AWA_2010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT2010 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2010_AWA_2010>");
        LNB2010_AWA_2010 = (LNB2010_AWA_2010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_ULNM();
        B900_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB2010_AWA_2010.CONT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB2010_AWA_2010.CONT_NO;
        CEP.TRC(SCCGWA, LNB2010_AWA_2010.CONT_NO);
        T000_READ_LNTCONT();
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'R';
        CEP.TRC(SCCGWA, LNB2010_AWA_2010.CONT_NO);
        LNRICTL.KEY.CONTRACT_NO = LNB2010_AWA_2010.CONT_NO;
        S000_CALL_LNZRICTL();
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UPD_RES_REC, WS_TEMP_VARIABLE.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UPD_MTDW_CONT, WS_TEMP_VARIABLE.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
            }
        } else {
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UPD_RES_REC, WS_TEMP_VARIABLE.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
            }
        }
        if (LNB2010_AWA_2010.GRA_DAYA != 0 
            && LNB2010_AWA_2010.GRA_PDFL != 'Y' 
            && LNB2010_AWA_2010.GRA_PDFL != 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GRA_PDFL_I, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        if (LNB2010_AWA_2010.GRA_DAYA != 0 
            && LNB2010_AWA_2010.P_GRA_MT == ' ' 
            && LNB2010_AWA_2010.C_GRA_MT == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GUR_TYP_I, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        if (LNB2010_AWA_2010.GUARDUAP != ' ' 
            && LNB2010_AWA_2010.GUARPSEQ == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GUA_SEQ, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        if (LNB2010_AWA_2010.HAND_CHR != 0 
            && LNB2010_AWA_2010.HAND_MTH == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_HAND_MTH_I, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        if (LNB2010_AWA_2010.INT_D_BA.trim().length() == 0 
            || !LNB2010_AWA_2010.INT_D_BA.equalsIgnoreCase("01") 
            && !LNB2010_AWA_2010.INT_D_BA.equalsIgnoreCase("02") 
            && !LNB2010_AWA_2010.INT_D_BA.equalsIgnoreCase("03") 
            && !LNB2010_AWA_2010.INT_D_BA.equalsIgnoreCase("04") 
            && !LNB2010_AWA_2010.INT_D_BA.equalsIgnoreCase("05")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INT_D_BA, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        if (LNRCONT.MAT_DATE <= SCCGWA.COMM_AREA.AC_DATE 
            && LNB2010_AWA_2010.GRA_DAYA != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GRA_DAYA_I, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_ULNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCULNM);
        LNCULNM.COMM_DATA.CONTRACT_NO = LNB2010_AWA_2010.CONT_NO;
        LNCULNM.COMM_DATA.SUF_NO = 0;
        CEP.TRC(SCCGWA, LNRCONT.CCY);
        LNCULNM.COMM_DATA.CCY = LNRCONT.CCY;
        LNCULNM.COMM_DATA.ACCRUAL_TYPE = LNB2010_AWA_2010.AUAL_TYP;
        LNCULNM.COMM_DATA.INT_DAY_BAS = LNB2010_AWA_2010.INT_D_BA;
        LNCULNM.COMM_DATA.GUAR_DUE_AP = LNB2010_AWA_2010.GUARDUAP;
        LNCULNM.COMM_DATA.GUAR_PAY_SEQ = LNB2010_AWA_2010.GUARPSEQ;
        LNCULNM.COMM_DATA.GRA_DAYS_ACC = LNB2010_AWA_2010.GRA_DAYA;
        LNCULNM.COMM_DATA.GRA_PDFL = LNB2010_AWA_2010.GRA_PDFL;
        LNCULNM.COMM_DATA.BAT_FLG = LNB2010_AWA_2010.BAT_FLG;
        LNCULNM.COMM_DATA.P_GRA_MTH = LNB2010_AWA_2010.P_GRA_MT;
        LNCULNM.COMM_DATA.I_GRA_MTH = LNB2010_AWA_2010.C_GRA_MT;
        LNCULNM.COMM_DATA.HAND_CHG_RATE = LNB2010_AWA_2010.HAND_CHR;
        LNCULNM.COMM_DATA.HAND_MTH = LNB2010_AWA_2010.HAND_MTH;
        LNCULNM.COMM_DATA.HAND_CHG_MTH = LNB2010_AWA_2010.HAND_CHM;
        LNCULNM.COMM_DATA.PFE_MTH = LNB2010_AWA_2010.PFE_MTH;
        LNCULNM.COMM_DATA.PERD_PENT = LNB2010_AWA_2010.PERD_PEN;
        LNCULNM.COMM_DATA.HAND_OVR_PCT = LNB2010_AWA_2010.HANDOPCT;
        LNCULNM.COMM_DATA.PREP_CHR = LNB2010_AWA_2010.PREP_CHR;
        LNCULNM.COMM_DATA.REMARK1 = LNB2010_AWA_2010.REMARK1;
        LNCULNM.COMM_DATA.PSEQ_CD = LNB2010_AWA_2010.PSEQ_CD;
        S000_CALL_LNZULNM();
    }
    public void B900_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 181;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLGS.WS_READ_LNTLOAN_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLGS.WS_READ_LNTLOAN_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTLOAN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLGS.WS_READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLGS.WS_READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLGS.WS_READ_LNTAPRD_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLGS.WS_READ_LNTAPRD_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTAPRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLGS.WS_READ_LNTPAIP_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLGS.WS_READ_LNTPAIP_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTPAIP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_LNZPAIP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-R-PAIPL-MAIN", LNCRPAIP);
        if (LNCRPAIP.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG.WS_MSG_AP = LNCRPAIP.RC.RC_MMO;
            WS_TEMP_VARIABLE.WS_ERR_MSG.WS_MSG_CODE = LNCRPAIP.RC.RC_CODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG.WS_MSG_AP = "SC";
            WS_TEMP_VARIABLE.WS_ERR_MSG.WS_MSG_CODE = SCCCLDT.RC;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZULNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-LOAN-MODIFY", LNCULNM);
        if (LNCULNM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG.WS_MSG_AP = LNCULNM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_ERR_MSG.WS_MSG_CODE = LNCULNM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC_W() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
    }
    public void T000_READ_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRRATN() throws IOException,SQLException,Exception {
        LNCRRATN.REC_PTR = LNRRATN;
        LNCRRATN.REC_LEN = 423;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRATN", LNCRRATN);
        if (LNCRRATN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRRATN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.col = "CONTRACT_NO,";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO";
        LNTAGRE_RD.fst = true;
        LNTAGRE_RD.order = "NEXT_SEQ DESC";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CONT_NOTFND, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_LNTRATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND "
            + "AND ACTV_DT = :LNRRATL.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATL_NOTFND, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRCMMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        CEP.TRC(SCCGWA, LNCRCMMT.RC.RC_CODE);
        if (LNCRCMMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, LNCRCMMT.RETURN_INFO);
        CEP.TRC(SCCGWA, LNCRCMMT.RC);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-PEND-MGM", BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
