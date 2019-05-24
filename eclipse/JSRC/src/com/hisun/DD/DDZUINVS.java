package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.FS.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUINVS {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm FSTVMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTMST_RD;
    String WS_VS_AC = " ";
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    String WS_DD_AC = " ";
    int WS_RCD_SEQ = 0;
    int WS_TOTAL_NUM = 0;
    char WS_VTL_AC_STS = ' ';
    String K_OUTPUT_FMT_B = "DD830";
    String K_OUTPUT_FMT_D = "DD831";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    String WS_CUS_STMS = "                              ";
    short WS_ARRAY_IDX = 0;
    short WS_ARRAY_CNT = 0;
    int WS_TOTAL_PAGE = 0;
    int WS_REMAINDER = 0;
    String WS_PARENT_AC = " ";
    DDZUINVS_WS1_SPE_DATA[] WS1_SPE_DATA = new DDZUINVS_WS1_SPE_DATA[3];
    char WS_TBL_FLAG = ' ';
    char WS_TBL_FLG_1 = ' ';
    DDZUINVS_WS_FMT_BROWSE WS_FMT_BROWSE = new DDZUINVS_WS_FMT_BROWSE();
    DDZUINVS_WS_FMT_DETAIL WS_FMT_DETAIL = new DDZUINVS_WS_FMT_DETAIL();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    DDCOINBR DDCOINBR = new DDCOINBR();
    DDCOINDE DDCOINDE = new DDCOINDE();
    CICQVAC CICQVAC = new CICQVAC();
    FSRVMST FSRVMST = new FSRVMST();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    CIRACRL CIRACRL = new CIRACRL();
    SCCGWA SCCGWA;
    DDCUINVS DDCUINVS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public DDZUINVS() {
        for (int i=0;i<3;i++) WS1_SPE_DATA[i] = new DDZUINVS_WS1_SPE_DATA();
    }
    public void MP(SCCGWA SCCGWA, DDCUINVS DDCUINVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUINVS = DDCUINVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUINVS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_TBL_FLAG = 'Y';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUINVS.I_FUNC);
        CEP.TRC(SCCGWA, DDCUINVS.PAGE_ROW);
        CEP.TRC(SCCGWA, DDCUINVS.PAGE_NUM);
        if (DDCUINVS.I_FUNC == '1') {
            CEP.TRC(SCCGWA, DDCUINVS.PARE_AC);
            B100_BROWSE_LIST();
        } else if (DDCUINVS.I_FUNC == '2') {
            CEP.TRC(SCCGWA, DDCUINVS.VS_AC);
            CEP.TRC(SCCGWA, DDCUINVS.CCY);
            CEP.TRC(SCCGWA, DDCUINVS.CCY_TYP);
            B200_INQUIRY_DETAIL();
        } else if (DDCUINVS.I_FUNC == '4') {
            B300_INQUIRY_FSRVMST();
        }
    }
    public void B100_BROWSE_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CEP.TRC(SCCGWA, DDCUINVS.PARE_AC);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCUINVS.PARE_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        DDCUINVS.PARE_AC = CICQACRI.O_DATA.O_AGR_NO;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUINVS.PARE_AC;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        T000_READ_DDTMST();
        WS_DD_AC = DDCUINVS.PARE_AC;
        WS_VTL_AC_STS = DDCUINVS.VS_STS;
        T400_GET_TOTAL_NUM_PAGE();
        B101_GET_DATA();
    }
    public void B200_INQUIRY_DETAIL() throws IOException,SQLException,Exception {
        WS_VS_AC = DDCUINVS.VS_AC;
        if (DDCUINVS.CCY.trim().length() == 0) {
            WS_CCY = "156";
        } else {
            WS_CCY = DDCUINVS.CCY;
        }
        if (WS_CCY.equalsIgnoreCase("156")) {
            WS_CCY_TYP = '1';
        } else {
            WS_CCY_TYP = '2';
        }
        CEP.TRC(SCCGWA, WS_VTL_AC_STS);
        CEP.TRC(SCCGWA, "SUZHNN-405");
        T000_READ_FSTVMST();
        IBS.init(SCCGWA, CICQVAC);
        CICQVAC.FUNC = '2';
        CICQVAC.DATA.AC_NO = DDCUINVS.VS_AC;
        CICQVAC.DATA.AC_REL = "01";
        S000_CALL_CIZQVAC();
        WS_PARENT_AC = CICQVAC.OUT_DATA.REL_INF[1-1].O_REL_AC_NO;
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.REL_INF[1-1].O_AC_REL);
        CEP.TRC(SCCGWA, WS_PARENT_AC);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = WS_PARENT_AC;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        T000_READ_DDTMST();
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_PARENT_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        IBS.init(SCCGWA, DDRCCY);
        WS_VS_AC = DDCUINVS.VS_AC;
        WS_CCY = DDCUINVS.CCY;
        WS_CCY_TYP = DDCUINVS.CCY_TYP;
        T000_READ_DDTCCY();
        B201_DETAIL_FMT_OUTPUT();
    }
    public void B300_INQUIRY_FSRVMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVMST);
        IBS.init(SCCGWA, DDRMST);
        WS_VS_AC = DDCUINVS.VS_AC;
        if (DDCUINVS.CCY.trim().length() == 0) {
            WS_CCY = "156";
        } else {
            WS_CCY = DDCUINVS.CCY;
        }
        if (WS_CCY.equalsIgnoreCase("156")) {
            WS_CCY_TYP = '1';
        } else {
            WS_CCY_TYP = '2';
        }
        T000_READ_FSTVMST();
        T000_READ_DDTCCY();
        DDCUINVS.O_AC_NAME = FSRVMST.ACC_NAME;
        DDCUINVS.O_DRAW_MTH = '5';
        DDCUINVS.O_ACO_STS = DDRCCY.STS;
        DDCUINVS.O_ACO_STSW = DDRCCY.STS_WORD;
        DDCUINVS.O_ACO_OPE_BR = DDRCCY.OPEN_DP;
        DDCUINVS.O_ACO_OWN_BR = DDRCCY.OWNER_BR;
        DDCUINVS.O_LAST_BAL = DDRCCY.LAST_BAL;
        DDCUINVS.O_AVA_BAL = DDRCCY.CURR_BAL;
        DDCUINVS.O_CUR_BAL = DDRCCY.CURR_BAL;
        DDCUINVS.O_CCY = DDRCCY.CCY;
        DDCUINVS.O_CCY_TYP = DDRCCY.CCY_TYPE;
        DDCUINVS.O_OWNER_BR = DDRCCY.OWNER_BR;
        CEP.TRC(SCCGWA, DDCUINVS.O_OWNER_BR);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
    }
    public void B101_GET_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_BROWSE);
        WS_FMT_BROWSE.WS_FMT_B_P_AC = DDCUINVS.PARE_AC;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUINVS.PARE_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            WS_FMT_BROWSE.WS_FMT_B_P_AC_NM = CICACCU.DATA.AC_ENM;
        } else {
            WS_FMT_BROWSE.WS_FMT_B_P_AC_NM = CICACCU.DATA.AC_CNM;
        }
        WS_ARRAY_IDX = 1;
        while (WS_TBL_FLAG != 'N' 
            && WS_ARRAY_IDX <= DDCUINVS.PAGE_ROW) {
            CEP.TRC(SCCGWA, WS_ARRAY_IDX);
            CEP.TRC(SCCGWA, WS_RCD_SEQ);
            FSRVMST.KEY.ACC_NO = CICQVAC.OUT_DATA.REL_INF[WS_ARRAY_IDX-1].O_AC_NO;
            T000_READ_NEW_FSTVMST();
            WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_VS_AC = FSRVMST.KEY.ACC_NO;
            WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_CCY = FSRVMST.CCY;
            WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_CCY_TYP = FSRVMST.CCY_TYP;
            WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_VS_AC_NM = FSRVMST.ACC_NAME;
            WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_OP_DT = FSRVMST.CRT_DATE;
            WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_CLO_DT = 0;
            WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_VS_STS = ' ';
            WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_REMARK = FSRVMST.REMARK;
            CEP.TRC(SCCGWA, WS_FMT_BROWSE.WS_FMT_B_DATA[WS_ARRAY_IDX-1].WS_FMT_B_VS_AC);
            WS_ARRAY_IDX += 1;
        }
        WS_FMT_BROWSE.WS_FMT_B_CURR_PAGE = CICQVAC.OUT_DATA.CURR_PAGE;
        WS_FMT_BROWSE.WS_FMT_B_TOTAL_PAGE = CICQVAC.OUT_DATA.TOTAL_PAGE;
        WS_FMT_BROWSE.WS_FMT_B_TOTAL_NUM = CICQVAC.OUT_DATA.TOTAL_NUM;
        WS_FMT_BROWSE.WS_FMT_B_PAGE_ROW = CICQVAC.OUT_DATA.CURR_PAGE_ROW;
        CEP.TRC(SCCGWA, WS_FMT_BROWSE.WS_FMT_B_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_FMT_BROWSE.WS_FMT_B_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_FMT_BROWSE.WS_FMT_B_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_FMT_BROWSE.WS_FMT_B_PAGE_ROW);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_B;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_FMT_BROWSE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCOINBR.FMT_BROWSE);
        SCCFMT.DATA_PTR = DDCOINBR;
        SCCFMT.DATA_LEN = 2742;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B201_DETAIL_FMT_OUTPUT() throws IOException,SQLException,Exception {
        WS_FMT_DETAIL.WS_FMT_VS_AC = FSRVMST.KEY.ACC_NO;
        WS_FMT_DETAIL.WS_FMT_AC_STS = ' ';
        WS_FMT_DETAIL.WS_FMT_PARENT_AC = WS_PARENT_AC;
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            WS_FMT_DETAIL.WS_FMT_P_AC_NAME = CICACCU.DATA.AC_ENM;
        } else {
            WS_FMT_DETAIL.WS_FMT_P_AC_NAME = CICACCU.DATA.AC_CNM;
        }
        WS_FMT_DETAIL.WS_FMT_CCY = FSRVMST.CCY;
        WS_FMT_DETAIL.WS_FMT_CCY_TYP = FSRVMST.CCY_TYP;
        WS_FMT_DETAIL.WS_FMT_VS_CURR_BAL = DDRCCY.CURR_BAL;
        DDCUINVS.BAL = DDRCCY.CURR_BAL;
        WS_FMT_DETAIL.WS_FMT_VS_AC_NAME = FSRVMST.ACC_NAME;
        WS_FMT_DETAIL.WS_FMT_VS_CON_NAME = " ";
        WS_FMT_DETAIL.WS_FMT_VS_CON_TEL = " ";
        WS_FMT_DETAIL.WS_FMT_VS_CON_ADDR = " ";
        WS_FMT_DETAIL.WS_FMT_OPEN_DT = FSRVMST.CRT_DATE;
        WS_FMT_DETAIL.WS_FMT_CLOSE_DT = 0;
        WS_FMT_DETAIL.WS_FMT_REMARK = FSRVMST.REMARK;
        WS_FMT_DETAIL.WS_FMT_OPDP_OTH = "" + DDRMST.OPEN_DP;
        JIBS_tmp_int = WS_FMT_DETAIL.WS_FMT_OPDP_OTH.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_FMT_DETAIL.WS_FMT_OPDP_OTH = "0" + WS_FMT_DETAIL.WS_FMT_OPDP_OTH;
        WS_FMT_DETAIL.WS_FMT_VS_IDTYP = " ";
        WS_FMT_DETAIL.WS_FMT_VS_IDNO = " ";
        WS_FMT_DETAIL.WS_FMT_VS_MMO = " ";
        WS_FMT_DETAIL.WS_FMT_VS_FLG = ' ';
        WS_FMT_DETAIL.WS_FMT_VS_CHNLNO = " ";
        WS_FMT_DETAIL.WS_FMT_VS_INTAC = " ";
        WS_FMT_DETAIL.WS_FMT_VS_SYSNO = " ";
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_AC);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_AC_STS);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_PARENT_AC);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_P_AC_NAME);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_CCY);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_CCY_TYP);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_AC_NAME);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_CON_NAME);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_CON_TEL);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_CON_ADDR);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_OPEN_DT);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_CLOSE_DT);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_OPDP_OTH);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_IDTYP);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_IDNO);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_MMO);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_FLG);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_CHNLNO);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_INTAC);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_SYSNO);
        CEP.TRC(SCCGWA, WS_FMT_DETAIL.WS_FMT_VS_CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_D;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_FMT_DETAIL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCOINDE.FMT_DETAIL);
        SCCFMT.DATA_PTR = DDCOINDE;
        SCCFMT.DATA_LEN = 1442;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_CALL_CIZQVAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-VIR-AC", CICQVAC);
        if (CICQVAC.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQVAC.RC);
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            CEP.ERR(SCCGWA, CICACCU.RC);
        }
    }
    public void T000_READ_NEW_FSTVMST() throws IOException,SQLException,Exception {
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        FSTVMST_RD.where = "ACC_NO = :WS_VS_AC";
        IBS.READ(SCCGWA, FSRVMST, this, FSTVMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_FSTVMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUINVS);
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        FSTVMST_RD.where = "ACC_NO = :WS_VS_AC";
        IBS.READ(SCCGWA, FSRVMST, this, FSTVMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_NO_RSLT;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :WS_VS_AC "
            + "AND CCY = :WS_CCY "
            + "AND CCY_TYPE = :WS_CCY_TYP";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_NO_RSLT;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_DDAC_NOT_FND;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDRMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T400_GET_TOTAL_NUM_PAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQVAC);
        CEP.TRC(SCCGWA, DDCUINVS.PAGE_NUM);
        CICQVAC.FUNC = '1';
        CICQVAC.DATA.REL_AC_NO = DDCUINVS.PARE_AC;
        CICQVAC.DATA.AC_REL = "01";
        CICQVAC.DATA.PAGE_NUM = DDCUINVS.PAGE_NUM;
        CICQVAC.DATA.PAGE_ROW = DDCUINVS.PAGE_ROW;
        CICQVAC.DATA.REL_STS = DDCUINVS.VS_STS;
        if (DDCUINVS.VS_STS == 'N') {
            CICQVAC.DATA.REL_STS = '0';
        }
        if (DDCUINVS.VS_STS == 'C') {
            CICQVAC.DATA.REL_STS = '1';
        }
        CEP.TRC(SCCGWA, CICQVAC.DATA.REL_AC_NO);
        CEP.TRC(SCCGWA, CICQVAC.DATA.AC_REL);
        CEP.TRC(SCCGWA, CICQVAC.DATA.PAGE_NUM);
        CEP.TRC(SCCGWA, CICQVAC.DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, CICQVAC.DATA.REL_STS);
        S000_CALL_CIZQVAC();
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.TOTAL_PAGE);
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.TOTAL_NUM);
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.CURR_PAGE);
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.CURR_PAGE_ROW);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
