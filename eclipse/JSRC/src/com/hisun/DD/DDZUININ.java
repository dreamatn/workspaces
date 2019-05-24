package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUININ {
    SimpleDateFormat JIBS_sdf;
    SimpleDateFormat JIBS_sdfD;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTVSABI_RD;
    DBParm DDTMST_RD;
    brParm DDTVSBAL_BR = new brParm();
    DBParm DDTVSBAL_RD;
    String WS_VS_AC = " ";
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    int WS_ST_DT = 0;
    int WS_ED_DT = 0;
    int WS_LAST_DT = 0;
    String K_OUTPUT_FMT = "DD850";
    char K_MAIN_AC_CLO = 'C';
    String PGM_BPZQCCY = "BPZQCCY";
    String K_INT_360 = "01";
    String K_INT_365 = "02";
    String K_INT_366 = "03";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    String WS_CUS_STMS = "                              ";
    double WS_INT = 0;
    double WS_INST_TRAILER = 0;
    double WS_INST_BODY = 0;
    double WS_INST_HEAD = 0;
    int WS_TRAN_DT = 0;
    int WS_TRAN_DT_B = 0;
    int WS_NUM_BTWN_DATE = 0;
    int WS_INT_METHOD = 36000;
    int WS_FIRST_ED_DT = 0;
    double WS_LAST_BAL = 0;
    double WS_AMT_SUM = 0;
    char WS_DDTVSABI_FLG = ' ';
    char WS_DDTVSBAL_FLG = ' ';
    char WS_FIRST_REC_FLG = ' ';
    char WS_LAST_REC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRVSABI DDRVSABI = new DDRVSABI();
    DDRVSBAL DDRVSBAL = new DDRVSBAL();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDCUININ DDCUININ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCUININ DDCUININ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUININ = DDCUININ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUININ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_DDTVSABI_FLG = 'Y';
        IBS.init(SCCGWA, DDCUININ.OUTPUT);
        CEP.TRC(SCCGWA, DDCUININ.INPUT.VS_AC);
        CEP.TRC(SCCGWA, DDCUININ.INPUT.CCY);
        CEP.TRC(SCCGWA, DDCUININ.INPUT.CCY_TYP);
        CEP.TRC(SCCGWA, DDCUININ.INPUT.ST_DT);
        CEP.TRC(SCCGWA, DDCUININ.INPUT.ED_DT);
        CEP.TRC(SCCGWA, DDCUININ.INPUT.INT_RAT);
        CEP.TRC(SCCGWA, DDCUININ.INPUT.INT_RAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_REQ();
        B200_CAL_INT();
    }
    public void B100_CHECK_REQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVSABI);
        DDRVSABI.KEY.VS_AC = DDCUININ.INPUT.VS_AC;
        DDRVSABI.KEY.CCY = DDCUININ.INPUT.CCY;
        DDRVSABI.KEY.CCY_TYP = DDCUININ.INPUT.CCY_TYP;
        T000_READ_DDTVSABI();
        if (DDRVSABI.AC_STS == 'C') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_CLO;
            S000_ERR_MSG_PROC();
        }
        if (DDCUININ.INPUT.ST_DT > DDRVSABI.OPEN_DT) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_REQ_DT_INVALID;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDRVSABI.KEY.VS_AC;
        T010_READ_DDTMST();
        if (DDRMST.AC_STS == K_MAIN_AC_CLO) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDRVSABI.KEY.VS_AC;
        S000_CALL_CIZACCU();
        if (WS_CUS_STMS.equalsIgnoreCase("DEF")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CUSTOM_CLOSE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CAL_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCUININ.INPUT.CCY;
        S000_CALL_BPZQCCY();
        if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase(K_INT_360)) {
            WS_INT_METHOD = 36000;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase(K_INT_365)) {
            WS_INT_METHOD = 36500;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase(K_INT_366)) {
            WS_INT_METHOD = 36600;
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_BPZQCCY_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, WS_INT_METHOD);
        WS_VS_AC = DDCUININ.INPUT.VS_AC;
        WS_CCY = DDCUININ.INPUT.CCY;
        WS_CCY_TYP = DDCUININ.INPUT.CCY_TYP;
        WS_ST_DT = DDCUININ.INPUT.ST_DT;
        WS_ED_DT = DDCUININ.INPUT.ED_DT;
        T020_STARTBR_DDTVSBAL();
        T030_READNEXT_DDTVSBAL();
        if (WS_DDTVSBAL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ZERO HERE");
            T040_ENDBR_DDTVSBAL();
            B210_CAL_INT();
        } else {
            CEP.TRC(SCCGWA, "OTHE HERE");
            B220_CAL_INT();
            T040_ENDBR_DDTVSBAL();
        }
        CEP.TRC(SCCGWA, DDCUININ.OUTPUT.INT);
    }
    public void B210_CAL_INT() throws IOException,SQLException,Exception {
        T050_STARTBR_DDTVSBAL_FIRST();
        if (WS_FIRST_REC_FLG == 'Y') {
            SimpleDateFormat JIBS_sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat JIBS_sdfD = new SimpleDateFormat("yyyyD");
            JIBS_tmp_str[0] = "" + DDCUININ.INPUT.ED_DT;
            SimpleDateFormat JIBS_sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat JIBS_sdfD = new SimpleDateFormat("yyyyD");
            JIBS_tmp_str[1] = "" + DDCUININ.INPUT.ST_DT;
            WS_NUM_BTWN_DATE = Integer.parseInt(JIBS_sdfD.format(JIBS_sdf.parse(JIBS_tmp_str[0]))) - Integer.parseInt(JIBS_sdfD.format(JIBS_sdf.parse(JIBS_tmp_str[1])));
            DDCUININ.OUTPUT.INT = DDRVSBAL.BAL * WS_NUM_BTWN_DATE * DDCUININ.INPUT.INT_RAT / WS_INT_METHOD;
        } else {
            DDCUININ.OUTPUT.INT = 0;
        }
    }
    public void B220_CAL_INT() throws IOException,SQLException,Exception {
        DDCUININ.OUTPUT.INT = 0;
        WS_AMT_SUM = 0;
        WS_LAST_BAL = DDRVSBAL.BAL;
        WS_ST_DT = DDRVSBAL.KEY.VALUE_DT;
        WS_FIRST_ED_DT = DDRVSBAL.KEY.VALUE_DT;
        CEP.TRC(SCCGWA, DDRVSBAL.BAL);
        WS_LAST_DT = DDRVSBAL.LAST_BAL_DATE;
        T060_READ_DDTVSBAL();
        if (WS_LAST_REC_FLG == 'N') {
            DDRVSBAL.BAL = 0;
        }
        CEP.TRC(SCCGWA, DDRVSBAL.BAL);
        SimpleDateFormat JIBS_sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat JIBS_sdfD = new SimpleDateFormat("yyyyD");
        JIBS_tmp_str[0] = "" + WS_FIRST_ED_DT;
        SimpleDateFormat JIBS_sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat JIBS_sdfD = new SimpleDateFormat("yyyyD");
        JIBS_tmp_str[1] = "" + DDCUININ.INPUT.ST_DT;
        WS_NUM_BTWN_DATE = Integer.parseInt(JIBS_sdfD.format(JIBS_sdf.parse(JIBS_tmp_str[0]))) - Integer.parseInt(JIBS_sdfD.format(JIBS_sdf.parse(JIBS_tmp_str[1])));
        WS_AMT_SUM = WS_AMT_SUM + DDRVSBAL.BAL * WS_NUM_BTWN_DATE;
        T030_READNEXT_DDTVSBAL();
        while (WS_DDTVSBAL_FLG != 'N') {
            SimpleDateFormat JIBS_sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat JIBS_sdfD = new SimpleDateFormat("yyyyD");
            JIBS_tmp_str[0] = "" + DDRVSBAL.KEY.VALUE_DT;
            SimpleDateFormat JIBS_sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat JIBS_sdfD = new SimpleDateFormat("yyyyD");
            JIBS_tmp_str[1] = "" + WS_ST_DT;
            WS_NUM_BTWN_DATE = Integer.parseInt(JIBS_sdfD.format(JIBS_sdf.parse(JIBS_tmp_str[0]))) - Integer.parseInt(JIBS_sdfD.format(JIBS_sdf.parse(JIBS_tmp_str[1])));
            WS_AMT_SUM = WS_AMT_SUM + WS_LAST_BAL * WS_NUM_BTWN_DATE;
            WS_LAST_BAL = DDRVSBAL.BAL;
            WS_ST_DT = DDRVSBAL.KEY.VALUE_DT;
            T030_READNEXT_DDTVSBAL();
        }
        SimpleDateFormat JIBS_sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat JIBS_sdfD = new SimpleDateFormat("yyyyD");
        JIBS_tmp_str[0] = "" + DDCUININ.INPUT.ED_DT;
        SimpleDateFormat JIBS_sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat JIBS_sdfD = new SimpleDateFormat("yyyyD");
        JIBS_tmp_str[1] = "" + WS_ST_DT;
        WS_NUM_BTWN_DATE = Integer.parseInt(JIBS_sdfD.format(JIBS_sdf.parse(JIBS_tmp_str[0]))) - Integer.parseInt(JIBS_sdfD.format(JIBS_sdf.parse(JIBS_tmp_str[1])));
        WS_AMT_SUM = WS_AMT_SUM + WS_LAST_BAL * WS_NUM_BTWN_DATE;
        DDCUININ.OUTPUT.INT = WS_AMT_SUM * DDCUININ.INPUT.INT_RAT / WS_INT_METHOD;
    }
    public void T000_READ_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        IBS.READ(SCCGWA, DDRVSABI, DDTVSABI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VSAC_NOT_FND;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSABI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T010_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NOT_FOUND;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T020_STARTBR_DDTVSBAL() throws IOException,SQLException,Exception {
        DDTVSBAL_BR.rp = new DBParm();
        DDTVSBAL_BR.rp.TableName = "DDTVSBAL";
        DDTVSBAL_BR.rp.col = "VALUE_DT, BAL, LAST_BAL_DATE";
        DDTVSBAL_BR.rp.where = "VS_AC = :WS_VS_AC "
            + "AND CCY = :WS_CCY "
            + "AND CCY_TYP = :WS_CCY_TYP "
            + "AND VALUE_DT >= :WS_ST_DT "
            + "AND VALUE_DT <= :WS_ED_DT";
        DDTVSBAL_BR.rp.order = "VALUE_DT";
        IBS.STARTBR(SCCGWA, DDRVSBAL, this, DDTVSBAL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSBAL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T030_READNEXT_DDTVSBAL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRVSBAL, this, DDTVSBAL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTVSBAL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTVSBAL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSBAL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T040_ENDBR_DDTVSBAL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTVSBAL_BR);
    }
    public void T050_STARTBR_DDTVSBAL_FIRST() throws IOException,SQLException,Exception {
        DDTVSBAL_RD = new DBParm();
        DDTVSBAL_RD.TableName = "DDTVSBAL";
        DDTVSBAL_RD.col = "BAL";
        DDTVSBAL_RD.fst = true;
        DDTVSBAL_RD.where = "VS_AC = :WS_VS_AC "
            + "AND CCY = :WS_CCY "
            + "AND CCY_TYP = :WS_CCY_TYP "
            + "AND VALUE_DT < :WS_ST_DT";
        DDTVSBAL_RD.order = "VALUE_DT";
        IBS.READ(SCCGWA, DDRVSBAL, this, DDTVSBAL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FIRST_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FIRST_REC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSBAL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T060_READ_DDTVSBAL() throws IOException,SQLException,Exception {
        DDTVSBAL_RD = new DBParm();
        DDTVSBAL_RD.TableName = "DDTVSBAL";
        DDTVSBAL_RD.col = "BAL";
        DDTVSBAL_RD.where = "VS_AC = :WS_VS_AC "
            + "AND CCY = :WS_CCY "
            + "AND CCY_TYP = :WS_CCY_TYP "
            + "AND VALUE_DT = :WS_LAST_DT";
        IBS.READ(SCCGWA, DDRVSBAL, this, DDTVSBAL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LAST_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_LAST_REC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSBAL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
            WS_CUS_STMS = CICACCU.DATA.CI_STSW;
        } else {
            WS_MSG_ID = CICACCU.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        BPZQCCY BPZQCCY = new BPZQCCY();
        BPZQCCY.MP(SCCGWA, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE == 0) {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_BPZQCCY_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_MSG_ID, DDCUININ.OUTPUT.MSG_ID);
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCUININ.OUTPUT.MSG_ID.RES_RC != 0) {
            CEP.TRC(SCCGWA, "DDCUININ=");
            CEP.TRC(SCCGWA, DDCUININ);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
