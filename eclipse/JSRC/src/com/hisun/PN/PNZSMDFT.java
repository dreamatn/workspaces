package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class PNZSMDFT {
    DBParm PNTGBCC_RD;
    DBParm PNTDFT_RD;
    brParm PNTDFT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_KND = " ";
    String WS_DRFT_NO = " ";
    int WS_ISS_BR = 0;
    double WS_ISS_AMT1 = 0;
    double WS_ISS_AMT2 = 0;
    int WS_ISS_DT1 = 0;
    int WS_ISS_DT2 = 0;
    String WS_APP_AC = " ";
    String WS_APP_NAME = " ";
    char WS_STS = ' ';
    char WS_ODUE_FLG = ' ';
    int WS_RCD_SEQ = 0;
    int WS_CNT = 0;
    double WS_AMT = 0;
    String K_OUTPUT_FMT = "PN250";
    String K_OUTPUT_FMT_B = "PNX01";
    short K_SUBR_ROW_CNT = 0;
    short K_MAX_COL_NO = 500;
    short K_SCR_ROW_NO = 10;
    short K_SCR_COL_CNT = 7;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_NUM = 0;
    short WS_A = 0;
    PNZSMDFT_WS_FMT WS_FMT = new PNZSMDFT_WS_FMT();
    PNZSMDFT_WS_TEMP WS_TEMP = new PNZSMDFT_WS_TEMP();
    char WS_TBL_FLAG = ' ';
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    PNCOMDFT PNCOMDFT = new PNCOMDFT();
    PNRDFT PNRDFT = new PNRDFT();
    PNRGBCC PNRGBCC = new PNRGBCC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNCSMDFT PNCSMDFT;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, PNCSMDFT PNCSMDFT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCSMDFT = PNCSMDFT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZSMDFT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, PNRDFT);
        WS_TBL_FLAG = 'Y';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (PNCSMDFT.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFT);
        PNRDFT.KEY.BILL_KND = PNCSMDFT.DATA.KEY.KND;
        PNRDFT.KEY.BILL_NO = PNCSMDFT.DATA.KEY.DRFT_NO;
        T000_READ_PNTDFT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_PNT_NOT_FND, PNCSMDFT.RC);
            WS_ERR_INFO = "SMDFT-KND=" + PNCSMDFT.DATA.KEY.KND + ",SMDFT-DRFT-NO=" + PNCSMDFT.DATA.KEY.DRFT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, PNRGBCC);
        PNRGBCC.KEY.BILL_KND = PNCSMDFT.DATA.KEY.KND;
        PNRGBCC.KEY.BILL_NO = PNCSMDFT.DATA.KEY.DRFT_NO;
        T000_READ_PNTGBCC();
        if (pgmRtn) return;
        B090_DATA_OUTPUT_1();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFT);
        WS_KND = PNCSMDFT.DATA.KEY.KND;
        WS_DRFT_NO = PNCSMDFT.DATA.KEY.DRFT_NO;
        WS_ISS_BR = PNCSMDFT.DATA.BR;
        WS_ISS_AMT1 = PNCSMDFT.DATA.ISS_AMT1;
        WS_ISS_AMT2 = PNCSMDFT.DATA.ISS_AMT2;
        WS_ISS_DT1 = PNCSMDFT.DATA.ISS_DT1;
        WS_ISS_DT2 = PNCSMDFT.DATA.ISS_DT2;
        WS_APP_AC = PNCSMDFT.DATA.APP_AC;
        WS_APP_NAME = PNCSMDFT.DATA.APP_ACNM;
        WS_STS = PNCSMDFT.DATA.STS;
        WS_ODUE_FLG = PNCSMDFT.DATA.ODUE_FLG;
        CEP.TRC(SCCGWA, "******************");
        CEP.TRC(SCCGWA, WS_KND);
        CEP.TRC(SCCGWA, WS_DRFT_NO);
        CEP.TRC(SCCGWA, WS_ISS_BR);
        CEP.TRC(SCCGWA, WS_ISS_AMT1);
        CEP.TRC(SCCGWA, WS_ISS_AMT2);
        CEP.TRC(SCCGWA, WS_ISS_DT1);
        CEP.TRC(SCCGWA, WS_ISS_DT2);
        CEP.TRC(SCCGWA, WS_APP_AC);
        CEP.TRC(SCCGWA, WS_APP_NAME);
        CEP.TRC(SCCGWA, WS_STS);
        CEP.TRC(SCCGWA, WS_ODUE_FLG);
        if (PNCSMDFT.DATA.KEY.DRFT_NO.trim().length() > 0) {
            WS_TEMP.WS_BROWSE_FLG = 'B';
        } else if (PNCSMDFT.DATA.APP_AC.trim().length() > 0) {
            WS_TEMP.WS_BROWSE_FLG = 'A';
        } else if (PNCSMDFT.DATA.APP_ACNM.trim().length() > 0) {
            WS_TEMP.WS_BROWSE_FLG = 'N';
        } else if (PNCSMDFT.DATA.BR != ' ') {
            WS_TEMP.WS_BROWSE_FLG = 'R';
        } else {
            CEP.TRC(SCCGWA, "----OTHER-----");
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR, PNCSMDFT.RC);
            WS_ERR_INFO = "WS-BROWSE-FLG=" + WS_TEMP.WS_BROWSE_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_COUNT_PNTDFT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TEMP.WS_BROWSE_FLG);
        T000_STARTBR_PNTDFT();
        if (pgmRtn) return;
        if (PNCSMDFT.DATA.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( PNCSMDFT.DATA.PAGE_NUM - 1 ) * K_SCR_ROW_NO + 1;
        }
        T000_READNEXT_PNTDFT_FIRST();
        if (pgmRtn) return;
        WS_NUM = 0;
        while (WS_NUM < K_SCR_ROW_NO 
            && WS_TBL_FLAG != 'N') {
            WS_NUM = (short) (WS_NUM + 1);
            WS_FMT.WS_DATA[WS_NUM-1].WS_KND1 = PNRDFT.KEY.BILL_KND;
            WS_FMT.WS_DATA[WS_NUM-1].WS_DRFT_NO1 = PNRDFT.KEY.BILL_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_STS1 = PNRDFT.STS;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AMT3 = PNRDFT.ISS_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_APP_AC1 = PNRDFT.APP_AC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ISS_BR1 = PNRDFT.ISS_BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ISS_DT3 = PNRDFT.ISS_DT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ODU_FLG1 = PNRDFT.ODUE_FLG;
            T000_READNEXT_PNTDFT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "HERE : ");
            CEP.TRC(SCCGWA, WS_NUM);
            CEP.TRC(SCCGWA, "HERE : ");
            CEP.TRC(SCCGWA, WS_TBL_FLAG);
        }
        T000_ENDBR_PNTDFT();
        if (pgmRtn) return;
        if (PNCSMDFT.DATA.PAGE_NUM == 0) {
            WS_FMT.WS_CURR_PAGE = 1;
        } else {
            WS_FMT.WS_CURR_PAGE = (short) PNCSMDFT.DATA.PAGE_NUM;
        }
        WS_FMT.WS_LAST_PAGE = 'N';
        CEP.TRC(SCCGWA, "SHI YE");
        if (WS_NUM <= K_SCR_ROW_NO 
            && WS_TBL_FLAG == 'N') {
            CEP.TRC(SCCGWA, "MOU YE");
            WS_FMT.WS_LAST_PAGE = 'Y';
        }
        WS_FMT.WS_PAGE_ROW = WS_NUM;
        WS_FMT.WS_SUM_AMT = WS_AMT;
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOMDFT);
        PNCOMDFT.DATA.KND = PNRDFT.KEY.BILL_KND;
        CEP.TRC(SCCGWA, PNCOMDFT.DATA.KND);
        PNCOMDFT.DATA.DRFT_NO = PNRDFT.KEY.BILL_NO;
        CEP.TRC(SCCGWA, PNCOMDFT.DATA.DRFT_NO);
        PNCOMDFT.DATA.OLD_DFNO = PNRDFT.OLD_DFNO;
        CEP.TRC(SCCGWA, PNCOMDFT.DATA.OLD_DFNO);
        PNCOMDFT.DATA.PAY_TYPE = PNRDFT.PAY_TYPE;
        CEP.TRC(SCCGWA, PNCOMDFT.DATA.PAY_TYPE);
        PNCOMDFT.DATA.ISS_DT = PNRDFT.ISS_DT;
        PNCOMDFT.DATA.DUE_DT = PNRDFT.DUE_DT;
        CEP.TRC(SCCGWA, PNCOMDFT.DATA.ISS_DT);
        PNCOMDFT.DATA.CLR_DATE = PNRDFT.CLR_DATE;
        PNCOMDFT.DATA.CHG_DATE = PNRDFT.CHG_DATE;
        PNCOMDFT.DATA.CCY = PNRDFT.ISS_CCY;
        PNCOMDFT.DATA.AMT = PNRDFT.ISS_AMT;
        PNCOMDFT.DATA.STL_AMT = PNRDFT.STL_AMT;
        PNCOMDFT.DATA.BAL_AMT = PNRDFT.BAL_AMT;
        PNCOMDFT.DATA.STS = PNRDFT.STS;
        PNCOMDFT.DATA.PAY_BK = PNRDFT.PAY_BK;
        PNCOMDFT.DATA.AGT_BK_NO = PNRDFT.AGT_BK_NO;
        PNCOMDFT.DATA.AGT_BK_NAME = PNRDFT.AGT_BK_NAME;
        PNCOMDFT.DATA.APP_ACNM = PNRDFT.APP_NAME;
        PNCOMDFT.DATA.PAYEE_ACNM = PNRDFT.PAYEE_NAME;
        PNCOMDFT.DATA.LHD_ACNM = PNRDFT.LHD_NAME;
        PNCOMDFT.DATA.TRN_FLG = PNRDFT.TRN_FLG;
        PNCOMDFT.DATA.C_T_FLG = PNRDFT.C_T_FLG;
        PNCOMDFT.DATA.ISS_FEEFLG = PNRDFT.FEE_FLG;
        if (PNRDFT.ODUE_FLG == '1') {
            PNCOMDFT.DATA.ODUE_FLG = '1';
        } else {
            PNCOMDFT.DATA.ODUE_FLG = '0';
        }
        PNCOMDFT.DATA.IO_FLG = PNRDFT.IO_FLG;
        PNCOMDFT.DATA.APP_AC = PNRDFT.APP_AC;
        PNCOMDFT.DATA.PAYEE_AC = PNRDFT.PAYEE_AC;
        PNCOMDFT.DATA.LHD_AC = PNRDFT.LHD_AC;
        PNCOMDFT.DATA.CR_AC = PNRDFT.CR_AC;
        if (PNRDFT.APB_TYPE == '1') {
            PNCOMDFT.DATA.APB_TYPE = '0';
        } else {
            PNCOMDFT.DATA.APB_TYPE = '1';
        }
        PNCOMDFT.DATA.APB_NO = PNRDFT.APB_NO;
        CEP.TRC(SCCGWA, PNRDFT.APB_NO);
        CEP.TRC(SCCGWA, PNCOMDFT.DATA.APB_NO);
        PNCOMDFT.DATA.APB_VALUE_DATE = PNRDFT.APB_VALUE_DATE;
        PNCOMDFT.DATA.ISS_BR = PNRDFT.ISS_BR;
        PNCOMDFT.DATA.ISS_TLR = PNRDFT.ISS_TLR;
        PNCOMDFT.DATA.CLR_BR = PNRDFT.CLR_BR;
        PNCOMDFT.DATA.CLR_TLR = PNRDFT.CLR_TLR;
        PNCOMDFT.DATA.USG_RMK = PNRDFT.USG_RMK;
        PNCOMDFT.DATA.STL_OPT = PNRDFT.STL_OPT;
        CEP.TRC(SCCGWA, PNCOMDFT.DATA.STL_OPT);
        PNCOMDFT.DATA.UPD_DT = PNRDFT.UPDTBL_DATE;
        PNCOMDFT.DATA.LOS_DATE = PNRGBCC.LOSE_DATE;
        PNCOMDFT.DATA.LOS_ADDR = PNRGBCC.LOSE_ADDR;
        PNCOMDFT.DATA.APP_NM = PNRGBCC.APP_NAME;
        PNCOMDFT.DATA.APP_ADDR = PNRGBCC.APP_ADDR;
        PNCOMDFT.DATA.APP_TEL = PNRGBCC.APP_TEL;
        PNCOMDFT.DATA.ID_TYPE = PNRGBCC.APP_ID_TYPE;
        PNCOMDFT.DATA.ID_NO = PNRGBCC.APP_ID_NO;
        PNCOMDFT.DATA.REASON = PNRGBCC.LOSE_RSN;
        PNCOMDFT.DATA.PAY_DATE = PNRGBCC.PAY_DATE;
        PNCOMDFT.DATA.FEE_FLG = PNRGBCC.FEE_FLG;
        PNCOMDFT.DATA.FEE_AC = PNRGBCC.FEE_AC;
        PNCOMDFT.DATA.STOP_DATE = PNRGBCC.STOP_DATE;
        PNCOMDFT.DATA.STOP_REASON = PNRGBCC.STOP_RSN;
        PNCOMDFT.DATA.STOP_REF_NP = PNRGBCC.STOP_REP_NO;
        PNCOMDFT.DATA.RM_NM = PNRGBCC.REL_NAME;
        PNCOMDFT.DATA.RM_ADDR = PNRGBCC.REL_ADDR;
        PNCOMDFT.DATA.RM_TEL = PNRGBCC.REL_TEL;
        PNCOMDFT.DATA.RM_IDTY = PNRGBCC.REL_ID_TYPE;
        PNCOMDFT.DATA.RM_IDNO = PNRGBCC.REL_ID_NO;
        PNCOMDFT.DATA.RM_RMK = PNRGBCC.REL_SMR;
        PNCOMDFT.DATA.MISS_DATE = PNRGBCC.MISS_DATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = PNCOMDFT;
        SCCFMT.DATA_LEN = 3125;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, PNCOMDFT);
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_B;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 2458;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_PNTGBCC() throws IOException,SQLException,Exception {
        PNTGBCC_RD = new DBParm();
        PNTGBCC_RD.TableName = "PNTGBCC";
        IBS.READ(SCCGWA, PNRGBCC, PNTGBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTGBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        IBS.READ(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PNTDFT() throws IOException,SQLException,Exception {
        if (WS_TEMP.WS_BROWSE_FLG == 'B') {
            PNTDFT_BR.rp = new DBParm();
            PNTDFT_BR.rp.TableName = "PNTDFT";
            PNTDFT_BR.rp.where = "( BILL_NO = :WS_DRFT_NO ) "
                + "AND ( BILL_KND = :WS_KND "
                + "OR ( :WS_KND = ' ' ) )";
            IBS.STARTBR(SCCGWA, PNRDFT, this, PNTDFT_BR);
        } else if (WS_TEMP.WS_BROWSE_FLG == 'A') {
            PNTDFT_BR.rp = new DBParm();
            PNTDFT_BR.rp.TableName = "PNTDFT";
            PNTDFT_BR.rp.where = "APP_AC = :WS_APP_AC "
                + "AND ( BILL_KND = :WS_KND "
                + "OR ( :WS_KND = ' ' ) ) "
                + "AND ( STS = :WS_STS "
                + "OR ( :WS_STS = ' ' ) ) "
                + "AND ( ISS_BR = :WS_ISS_BR "
                + "OR ( :WS_ISS_BR = 0 ) ) "
                + "AND ( ( ISS_DT BETWEEN :WS_ISS_DT1 "
                + "AND :WS_ISS_DT2 ) "
                + "OR :WS_ISS_DT2 = 0 ) "
                + "AND ( ( ISS_AMT BETWEEN :WS_ISS_AMT1 "
                + "AND :WS_ISS_AMT2 ) "
                + "OR :WS_ISS_AMT2 = 0 ) "
                + "AND ( ODUE_FLG = :WS_ODUE_FLG "
                + "OR ( :WS_ODUE_FLG = ' ' ) )";
            IBS.STARTBR(SCCGWA, PNRDFT, this, PNTDFT_BR);
        } else if (WS_TEMP.WS_BROWSE_FLG == 'N') {
            PNTDFT_BR.rp = new DBParm();
            PNTDFT_BR.rp.TableName = "PNTDFT";
            PNTDFT_BR.rp.where = "APP_NAME = :WS_APP_NAME "
                + "AND ( BILL_KND = :WS_KND "
                + "OR ( :WS_KND = ' ' ) ) "
                + "AND ( STS = :WS_STS "
                + "OR ( :WS_STS = ' ' ) ) "
                + "AND ( ISS_BR = :WS_ISS_BR "
                + "OR ( :WS_ISS_BR = 0 ) ) "
                + "AND ( ( ISS_DT BETWEEN :WS_ISS_DT1 "
                + "AND :WS_ISS_DT2 ) "
                + "OR :WS_ISS_DT2 = 0 ) "
                + "AND ( ( ISS_AMT BETWEEN :WS_ISS_AMT1 "
                + "AND :WS_ISS_AMT2 ) "
                + "OR :WS_ISS_AMT2 = 0 ) "
                + "AND ( ODUE_FLG = :WS_ODUE_FLG "
                + "OR ( :WS_ODUE_FLG = ' ' ) )";
            IBS.STARTBR(SCCGWA, PNRDFT, this, PNTDFT_BR);
        } else if (WS_TEMP.WS_BROWSE_FLG == 'R') {
            PNTDFT_BR.rp = new DBParm();
            PNTDFT_BR.rp.TableName = "PNTDFT";
            PNTDFT_BR.rp.where = "ISS_BR = :WS_ISS_BR "
                + "AND ( BILL_KND = :WS_KND "
                + "OR ( :WS_KND = ' ' ) ) "
                + "AND ( STS = :WS_STS "
                + "OR ( :WS_STS = ' ' ) ) "
                + "AND ( ( ISS_DT BETWEEN :WS_ISS_DT1 "
                + "AND :WS_ISS_DT2 ) "
                + "OR :WS_ISS_DT2 = 0 ) "
                + "AND ( ( ISS_AMT BETWEEN :WS_ISS_AMT1 "
                + "AND :WS_ISS_AMT2 ) "
                + "OR :WS_ISS_AMT2 = 0 ) "
                + "AND ( ODUE_FLG = :WS_ODUE_FLG "
                + "OR ( :WS_ODUE_FLG = ' ' ) )";
            IBS.STARTBR(SCCGWA, PNRDFT, this, PNTDFT_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR, PNCSMDFT.RC);
            WS_ERR_INFO = "WS-BROWSE-FLG" + WS_TEMP.WS_BROWSE_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_COUNT_PNTDFT() throws IOException,SQLException,Exception {
        if (WS_TEMP.WS_BROWSE_FLG == 'B') {
            PNTDFT_RD = new DBParm();
            PNTDFT_RD.TableName = "PNTDFT";
            PNTDFT_RD.set = "WS-CNT=COUNT(*),WS-AMT=IFNULL(SUM(ISS_AMT),0)";
            PNTDFT_RD.where = "( BILL_NO = :WS_DRFT_NO ) "
                + "AND ( BILL_KND = :WS_KND "
                + "OR ( :WS_KND = ' ' ) )";
            IBS.GROUP(SCCGWA, PNRDFT, this, PNTDFT_RD);
        } else if (WS_TEMP.WS_BROWSE_FLG == 'A') {
            PNTDFT_RD = new DBParm();
            PNTDFT_RD.TableName = "PNTDFT";
            PNTDFT_RD.set = "WS-CNT=COUNT(*),WS-AMT=IFNULL(SUM(ISS_AMT),0)";
            PNTDFT_RD.where = "APP_AC = :WS_APP_AC "
                + "AND ( BILL_KND = :WS_KND "
                + "OR ( :WS_KND = ' ' ) ) "
                + "AND ( STS = :WS_STS "
                + "OR ( :WS_STS = ' ' ) ) "
                + "AND ( ISS_BR = :WS_ISS_BR "
                + "OR ( :WS_ISS_BR = 0 ) ) "
                + "AND ( ( ISS_DT BETWEEN :WS_ISS_DT1 "
                + "AND :WS_ISS_DT2 ) "
                + "OR :WS_ISS_DT2 = 0 ) "
                + "AND ( ( ISS_AMT BETWEEN :WS_ISS_AMT1 "
                + "AND :WS_ISS_AMT2 ) "
                + "OR :WS_ISS_AMT2 = 0 ) "
                + "AND ( ODUE_FLG = :WS_ODUE_FLG "
                + "OR ( :WS_ODUE_FLG = ' ' ) )";
            IBS.GROUP(SCCGWA, PNRDFT, this, PNTDFT_RD);
        } else if (WS_TEMP.WS_BROWSE_FLG == 'N') {
            PNTDFT_RD = new DBParm();
            PNTDFT_RD.TableName = "PNTDFT";
            PNTDFT_RD.set = "WS-CNT=COUNT(*),WS-AMT=IFNULL(SUM(ISS_AMT),0)";
            PNTDFT_RD.where = "APP_NAME = :WS_APP_NAME "
                + "AND ( BILL_KND = :WS_KND "
                + "OR ( :WS_KND = ' ' ) ) "
                + "AND ( STS = :WS_STS "
                + "OR ( :WS_STS = ' ' ) ) "
                + "AND ( ISS_BR = :WS_ISS_BR "
                + "OR ( :WS_ISS_BR = 0 ) ) "
                + "AND ( ( ISS_DT BETWEEN :WS_ISS_DT1 "
                + "AND :WS_ISS_DT2 ) "
                + "OR :WS_ISS_DT2 = 0 ) "
                + "AND ( ( ISS_AMT BETWEEN :WS_ISS_AMT1 "
                + "AND :WS_ISS_AMT2 ) "
                + "OR :WS_ISS_AMT2 = 0 ) "
                + "AND ( ODUE_FLG = :WS_ODUE_FLG "
                + "OR ( :WS_ODUE_FLG = ' ' ) )";
            IBS.GROUP(SCCGWA, PNRDFT, this, PNTDFT_RD);
        } else if (WS_TEMP.WS_BROWSE_FLG == 'R') {
            PNTDFT_RD = new DBParm();
            PNTDFT_RD.TableName = "PNTDFT";
            PNTDFT_RD.set = "WS-CNT=COUNT(*),WS-AMT=IFNULL(SUM(ISS_AMT),0)";
            PNTDFT_RD.where = "ISS_BR = :WS_ISS_BR "
                + "AND ( BILL_KND = :WS_KND "
                + "OR ( :WS_KND = ' ' ) ) "
                + "AND ( STS = :WS_STS "
                + "OR ( :WS_STS = ' ' ) ) "
                + "AND ( ( ISS_DT BETWEEN :WS_ISS_DT1 "
                + "AND :WS_ISS_DT2 ) "
                + "OR :WS_ISS_DT2 = 0 ) "
                + "AND ( ( ISS_AMT BETWEEN :WS_ISS_AMT1 "
                + "AND :WS_ISS_AMT2 ) "
                + "OR :WS_ISS_AMT2 = 0 ) "
                + "AND ( ODUE_FLG = :WS_ODUE_FLG "
                + "OR ( :WS_ODUE_FLG = ' ' ) )";
            IBS.GROUP(SCCGWA, PNRDFT, this, PNTDFT_RD);
        } else {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR, PNCSMDFT.RC);
            WS_ERR_INFO = "WS-BROWSE-FLG" + WS_TEMP.WS_BROWSE_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "111111111111");
        if (WS_CNT == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_RECORD);
        }
        WS_FMT.WS_TOTAL_NUM = (short) WS_CNT;
        WS_A = (short) (WS_FMT.WS_TOTAL_NUM % K_SCR_ROW_NO);
        WS_FMT.WS_TOTAL_PAGE = (short) ((WS_FMT.WS_TOTAL_NUM - WS_A) / K_SCR_ROW_NO);
        if (WS_A > 0) {
            WS_FMT.WS_TOTAL_PAGE = (short) (WS_FMT.WS_TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void T000_READNEXT_PNTDFT_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PNRDFT, this, PNTDFT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PNTDFT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PNRDFT, this, PNTDFT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PNTDFT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PNTDFT_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, PNCSMDFT.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        Z_RET();
        if (pgmRtn) return;
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (PNCSMDFT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, PNCSMDFT);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
