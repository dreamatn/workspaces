package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.TD.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUHTOD {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTTTOD_RD;
    boolean pgmRtn = false;
    String WS_OVR_NO = " ";
    int WS_AC_DATE = 0;
    long WS_JRN_NO = 0;
    String WS_AC_NO = " ";
    String CPN_U_BPZCINTI = "BP-C-INTR-INQ";
    String K_PRDPR_TYPE = "PRDPR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    double WS_AMT = 0;
    double WS_PAYING_INT = 0;
    double WS_DD_BAL = 0;
    double WS_TD_START_AMT = 0;
    double WS_TOTAL_TXN_AMT = 0;
    double WS_DD_TXN_AMT = 0;
    double WS_TD_TXN_AMT = 0;
    double WS_LEFT_AMT = 0;
    double WS_TD_BAL = 0;
    int WS_CNT = 0;
    String WS_TD_AC = " ";
    double WS_TD_AMT = 0;
    String WS_DD_AC = " ";
    double WS_DD_AMT = 0;
    String WS_ACO_AC = " ";
    String WS_TD_PROD_CD = " ";
    int WS_DUE_DT = 0;
    char WS_TCCY_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_TTOD_FLG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCACE TDCACE = new TDCACE();
    DCRTTOD DCRTTOD = new DCRTTOD();
    DDRCCY DDRCCY = new DDRCCY();
    DCCUTTOD DCCUTTOD = new DCCUTTOD();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    TDCACDRU TDCACDRU = new TDCACDRU();
    TDCACCRU TDCACCRU = new TDCACCRU();
    TDCPRDP TDCPRDP = new TDCPRDP();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCRIRPRD DCRIRPRD = new DCRIRPRD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    TDCUGRP TDCUGRP = new TDCUGRP();
    DDCSPINT DDCSPINT = new DDCSPINT();
    TDRSMST TDRSMST = new TDRSMST();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCUHTOD DDCUHTOD;
    public void MP(SCCGWA SCCGWA, DDCUHTOD DDCUHTOD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUHTOD = DDCUHTOD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUHTOD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_DDDR_PROC();
        if (pgmRtn) return;
        B300_TDCR_PROC();
        if (pgmRtn) return;
        B600_WRITE_TTOD_HANDLE();
        if (pgmRtn) return;
    }
    public void B200_DDDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUHTOD.AC);
        CEP.TRC(SCCGWA, DDCUHTOD.CCY);
        CEP.TRC(SCCGWA, DDCUHTOD.CCY_TYP);
        CEP.TRC(SCCGWA, DDCUHTOD.PROD_CD);
        CEP.TRC(SCCGWA, DDCUHTOD.AMT);
        CEP.TRC(SCCGWA, DDCUHTOD.TERM);
        CEP.TRC(SCCGWA, DDCUHTOD.SPRD_PNT);
        CEP.TRC(SCCGWA, DDCUHTOD.SPRD_PCT);
        WS_DD_BAL = DDCUHTOD.AMT;
        CEP.TRC(SCCGWA, WS_DD_BAL);
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = DDCUHTOD.AC;
        if (DDCUHTOD.CCY.trim().length() == 0) {
            DDCUDRAC.CCY = "156";
        } else {
            DDCUDRAC.CCY = DDCUHTOD.CCY;
        }
        if (DDCUHTOD.CCY_TYP == ' ') {
            DDCUDRAC.CCY_TYPE = '1';
        } else {
            DDCUDRAC.CCY_TYPE = DDCUHTOD.CCY_TYP;
        }
        DDCUDRAC.TX_AMT = WS_DD_BAL;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_MMO = DDCUHTOD.TX_MMO;
        DDCUDRAC.AUTO_DDTOTD_FLG = 'Y';
        DDCUDRAC.SUPPLY_FLG = 'N';
        DDCUDRAC.HIS_SHOW_FLG = 'N';
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B300_TDCR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUHTOD.AC);
        CEP.TRC(SCCGWA, DDCUHTOD.TERM);
        CEP.TRC(SCCGWA, DDCUHTOD.TX_MMO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_JRN_NO);
        IBS.init(SCCGWA, TDCACCRU);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_AC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            WS_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            WS_AC_NO = DDCUHTOD.AC;
            T000_READ_UPT_DCTTTOD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRTTOD.KEY.TRC_AC);
            TDCACCRU.ACO_AC = DCRTTOD.KEY.TRC_AC;
            B400_UPDATE_DCTTTOD();
            if (pgmRtn) return;
        }
        TDCACCRU.AC_NO = DDCUHTOD.AC;
        TDCACCRU.OPT = '2';
        TDCACCRU.PRDMO_CD = "MMDP";
        TDCACCRU.OPSW_FLG = 'Y';
        TDCACCRU.PROD_CD = DDCUHTOD.PROD_CD;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = DDCUHTOD.AC;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        TDCACCRU.CI_NO = CICCUST.O_DATA.O_CI_NO;
        TDCACCRU.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        TDCACCRU.ID_NO = CICCUST.O_DATA.O_ID_NO;
        TDCACCRU.AC_NAME = CICCUST.O_DATA.O_CI_NM;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUHTOD.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            TDCACCRU.BV_TYP = '4';
        }
        TDCACCRU.OPP_AC_CNO = DDCUHTOD.AC;
        TDCACCRU.TXN_AMT = WS_DD_BAL;
        if (DDCUHTOD.CCY.trim().length() == 0) {
            TDCACCRU.CCY = "156";
        } else {
            TDCACCRU.CCY = DDCUHTOD.CCY;
        }
        if (DDCUHTOD.CCY_TYP == ' ') {
            TDCACCRU.CCY_TYP = '1';
        } else {
            TDCACCRU.CCY_TYP = DDCUHTOD.CCY_TYP;
        }
        TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        TDCACCRU.TERM = DDCUHTOD.TERM;
        TDCACCRU.SHOW = '1';
        if (DDCUHTOD.SPRD_PNT != 0) {
            TDCACCRU.SPRD_PNT = DDCUHTOD.SPRD_PNT;
            TDCACCRU.INT_SEL = '2';
        }
        if (DDCUHTOD.SPRD_PCT != 0) {
            TDCACCRU.SPRD_PCT = DDCUHTOD.SPRD_PCT;
            TDCACCRU.INT_SEL = '1';
        }
        S000_CALL_TDZACCRU();
        if (pgmRtn) return;
    }
    public void B400_UPDATE_DCTTTOD() throws IOException,SQLException,Exception {
        DCRTTOD.CZ_STS = 'S';
        DCRTTOD.TX_MMO = DDCUHTOD.TX_MMO;
        DCRTTOD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRTTOD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTTTOD();
        if (pgmRtn) return;
    }
    public void B600_WRITE_TTOD_HANDLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTTOD);
        DCRTTOD.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRTTOD.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        DCRTTOD.KEY.SEQ_NO = WS_CNT;
        DCRTTOD.TRC_AMT = WS_DD_BAL;
        DCRTTOD.TD_PROD_CODE = DDCUHTOD.PROD_CD;
        DCRTTOD.DD_AC = DDCUHTOD.AC;
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        DCRTTOD.KEY.TRC_AC = TDCACCRU.ACO_AC;
        DCRTTOD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRTTOD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRTTOD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRTTOD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTTTOD();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUHTOD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_TDZACCRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU, true);
    }
    public void T000_READ_UPT_DCTTTOD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, WS_JRN_NO);
        CEP.TRC(SCCGWA, WS_AC_NO);
        DCTTTOD_RD = new DBParm();
        DCTTTOD_RD.TableName = "DCTTTOD";
        DCTTTOD_RD.where = "'DATE' = :WS_AC_DATE "
            + "AND JRN_NO = :WS_JRN_NO "
            + "AND DD_AC = :WS_AC_NO";
        DCTTTOD_RD.upd = true;
        IBS.READ(SCCGWA, DCRTTOD, this, DCTTTOD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTTTOD() throws IOException,SQLException,Exception {
        DCTTTOD_RD = new DBParm();
        DCTTTOD_RD.TableName = "DCTTTOD";
        IBS.WRITE(SCCGWA, DCRTTOD, DCTTTOD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTTTOD() throws IOException,SQLException,Exception {
        DCTTTOD_RD = new DBParm();
        DCTTTOD_RD.TableName = "DCTTTOD";
        IBS.REWRITE(SCCGWA, DCRTTOD, DCTTTOD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUHTOD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, "DDCUHTOD=");
            CEP.TRC(SCCGWA, DDCUHTOD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
