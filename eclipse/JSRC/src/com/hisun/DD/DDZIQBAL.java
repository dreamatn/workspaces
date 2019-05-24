package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIQBAL {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTINTB_RD;
    DBParm DDTVCH_RD;
    brParm DDTDDTD_BR = new brParm();
    DBParm DDTADMN_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    double WS_CURR_BAL = 0;
    double WS_AVL_BAL = 0;
    double WS_DEP_INT = 0;
    double WS_NOT_INT_BAL = 0;
    double WS_AVA_BAL_TOT = 0;
    short WS_V_CNT = 0;
    double WS_VTD_BAL = 0;
    double WS_OD_ADP_BAL = 0;
    String WS_REL_AC = " ";
    char WS_DDTD_FLG = ' ';
    char WS_ADMN_READ_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRDDTD DDRDDTD = new DDRDDTD();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINTB DDRINTB = new DDRINTB();
    DDRMST DDRMST = new DDRMST();
    TDCACE TDCACE = new TDCACE();
    TDCUQAC TDCUQAC = new TDCUQAC();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRL CICQACRL = new CICQACRL();
    CICACCU CICACCU = new CICACCU();
    DDRVCH DDRVCH = new DDRVCH();
    DDRADMN DDRADMN = new DDRADMN();
    SCCGWA SCCGWA;
    DDCIQBAL DDCIQBAL;
    public void MP(SCCGWA SCCGWA, DDCIQBAL DDCIQBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIQBAL = DDCIQBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIQBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCIQBAL.RC.RC_MMO = "DD";
        DDCIQBAL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_ACAC_INFO();
        if (pgmRtn) return;
        B030_CHK_AC_INF_PROC();
        if (pgmRtn) return;
        B050_GET_CCY_BAL_PROC();
        if (pgmRtn) return;
        B070_GET_DEP_INT_PROC();
        if (pgmRtn) return;
        B090_GET_VTD_BAL_PROC();
        if (pgmRtn) return;
        B100_GET_LAST_PB_BAL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AC);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_TYPE);
        if (DDCIQBAL.DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIQBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCIQBAL.DATA.AC;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = DDCIQBAL.DATA.AC;
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_REL_AC = DDCIQBAL.DATA.AC;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = WS_REL_AC;
        CICQACAC.DATA.CCY_ACAC = DDCIQBAL.DATA.CCY;
        CICQACAC.DATA.CR_FLG = DDCIQBAL.DATA.CCY_TYPE;
        CICQACAC.DATA.AID = DDCIQBAL.DATA.AID;
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AID);
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B030_CHK_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            DDRMST.KEY.CUS_AC = WS_REL_AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
    }
    public void B050_GET_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, DDRADMN);
            DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, DDRADMN.KEY.AC);
            DDRADMN.ADP_STS = 'O';
            T000_READ_DDTADMN();
            if (pgmRtn) return;
            if (WS_ADMN_READ_FLG == 'Y') {
                CEP.TRC(SCCGWA, DDRADMN.OD_AMT);
                WS_OD_ADP_BAL = DDRADMN.OD_AMT;
                CEP.TRC(SCCGWA, WS_OD_ADP_BAL);
            }
        }
        CEP.TRC(SCCGWA, DDRADMN.KEY.AC);
        CEP.TRC(SCCGWA, DDRADMN.KEY.ADP_TYPE);
        CEP.TRC(SCCGWA, DDRADMN.ADP_STS);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        DDCIQBAL.DATA.AC_TYPE = DDRMST.AC_TYPE;
        if (DDRCCY.AC_FT != ' ') {
            DDCIQBAL.DATA.AC_TYPE = DDRCCY.AC_FT;
        }
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AC_TYPE);
        DDCIQBAL.DATA.CURR_BAL = DDRCCY.CURR_BAL;
        DDCIQBAL.DATA.HOLD_BAL = DDRCCY.HOLD_BAL;
        DDCIQBAL.DATA.NINT_BAL = DDRCCY.NOT_INT_BAL;
        DDCIQBAL.DATA.CCAL_TOT_BAL = DDRCCY.CCAL_TOT_BAL;
        DDCIQBAL.DATA.MARGIN_BAL = DDRCCY.MARGIN_BAL;
        DDCIQBAL.DATA.CLOSE_BAL = DDRCCY.CLOSE_BAL;
        DDCIQBAL.DATA.LAST_BAL = DDRCCY.LAST_BAL;
        DDCIQBAL.DATA.LAST_BAL_DATE = DDRCCY.LAST_BAL_DATE;
        DDCIQBAL.DATA.LAST_YEAR_BAL = DDRCCY.LAST_YEAR_BAL;
        DDCIQBAL.DATA.LST_DAY_YEAR_BAL = DDRCCY.LST_DAY_YEAR_BAL;
        DDCIQBAL.DATA.DEP_CAMT = DDRCCY.DEP_CAMT;
        DDCIQBAL.DATA.DRW_CAMT = DDRCCY.DRW_CAMT;
        DDCIQBAL.DATA.DEP_TAMT = DDRCCY.DEP_TAMT;
        DDCIQBAL.DATA.DRW_TAMT = DDRCCY.DRW_TAMT;
        DDCIQBAL.DATA.LAST_DEP_CAMT = DDRCCY.LAST_DEP_CAMT;
        DDCIQBAL.DATA.LAST_DRW_CAMT = DDRCCY.LAST_DRW_CAMT;
        DDCIQBAL.DATA.LAST_DEP_TAMT = DDRCCY.LAST_DEP_TAMT;
        DDCIQBAL.DATA.LAST_DRW_TAMT = DDRCCY.LAST_DRW_TAMT;
        DDCIQBAL.DATA.CCY_STS = DDRCCY.STS;
        DDCIQBAL.DATA.CCY_STS_WORD = DDRCCY.STS_WORD;
        DDCIQBAL.DATA.OPEN_DP = DDRCCY.OPEN_DP;
        DDCIQBAL.DATA.OWNER_BR = DDRCCY.OWNER_BR;
        DDCIQBAL.DATA.OWNER_BRDP = DDRCCY.OWNER_BRDP;
        WS_AVL_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
        CEP.TRC(SCCGWA, WS_AVL_BAL);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            DDCIQBAL.DATA.AVL_BAL = 0;
        } else {
            DDCIQBAL.DATA.AVL_BAL = WS_AVL_BAL;
        }
        if (WS_AVL_BAL < 0) {
            DDCIQBAL.DATA.AVL_BAL = WS_AVL_BAL;
        }
        DDCIQBAL.DATA.AVL_BAL = DDCIQBAL.DATA.AVL_BAL + WS_OD_ADP_BAL;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.HOLD_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.NINT_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCAL_TOT_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CLOSE_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.LAST_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.LAST_BAL_DATE);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.DEP_CAMT);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.DRW_CAMT);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.DEP_TAMT);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.DRW_TAMT);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.LAST_DEP_CAMT);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.LAST_DRW_CAMT);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.LAST_DEP_TAMT);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.LAST_DRW_TAMT);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
    }
    public void B070_GET_DEP_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRINTB.KEY.TYPE = 'D';
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        DDCIQBAL.DATA.DEP_INT = DDRINTB.DEP_ACCU_INT;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.DEP_INT);
    }
    public void B090_GET_VTD_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDDTD);
        IBS.init(SCCGWA, TDCACE);
        DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_STARTBR_DDTDDTD();
        if (pgmRtn) return;
        T000_READNEXT_DDTDDTD();
        if (pgmRtn) return;
        while (WS_DDTD_FLG != 'N') {
            WS_V_CNT += 1;
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.A_ACO_AC = DDRDDTD.KEY.CON_NO;
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.A_ACO_AC);
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            WS_VTD_BAL += TDCACE.DATA[1-1].BAL;
            T000_READNEXT_DDTDDTD();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTDDTD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VTD_BAL);
        DDCIQBAL.DATA.VTD_BAL = WS_VTD_BAL;
        DDCIQBAL.DATA.CURR_BAL_TOT = DDCIQBAL.DATA.VTD_BAL + DDRCCY.CCAL_TOT_BAL + DDRCCY.CURR_BAL;
    }
    public void B100_GET_LAST_PB_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCIQBAL.DATA.AC;
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        DDCIQBAL.DATA.LAST_PB_BAL = DDRVCH.LAST_PB_BAL;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.LAST_PB_BAL);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND, DDCIQBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCIQBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        }
    }
    public void T000_STARTBR_DDTDDTD() throws IOException,SQLException,Exception {
        WS_DDTD_FLG = 'N';
        DDTDDTD_BR.rp = new DBParm();
        DDTDDTD_BR.rp.TableName = "DDTDDTD";
        DDTDDTD_BR.rp.where = "AC = :DDRDDTD.KEY.AC";
        DDTDDTD_BR.rp.order = "CON_NO";
        IBS.STARTBR(SCCGWA, DDRDDTD, this, DDTDDTD_BR);
    }
    public void T000_READNEXT_DDTDDTD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRDDTD, this, DDTDDTD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTD_FLG = 'Y';
        } else {
            WS_DDTD_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTDDTD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTDDTD_BR);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLPGM(SCCGWA, "TDZACE", TDCACE, true);
        CEP.TRC(SCCGWA, "11111111111111111111111111");
        CEP.TRC(SCCGWA, TDCUQAC.RC_MSG.RC);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU, true);
    }
    public void T000_READ_DDTADMN() throws IOException,SQLException,Exception {
        WS_ADMN_READ_FLG = 'N';
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ADP_STS = :DDRADMN.ADP_STS";
        DDTADMN_RD.fst = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "XXXX1111");
            WS_ADMN_READ_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "XXXX2222");
            WS_ADMN_READ_FLG = 'N';
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DDCIQBAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
