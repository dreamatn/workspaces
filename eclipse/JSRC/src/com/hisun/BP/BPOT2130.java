package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2130 {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTALIB_RD;
    DBParm BPTAPAR_RD;
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_S_IN_STORE = "BP-S-IN-STORE       ";
    String CPN_P_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE   ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_INQ_MOVD = "BP-P-INQ-MOVD       ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String K_HIS_REMARKS = "BR-CS-MOV-IN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_INDEX = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_COUNT = 0;
    int WS_CNT = 0;
    double WS_GD_AMT = 0;
    int WS_CNT1 = 0;
    int WS_CNT2 = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    char WS_APP_TYPE = ' ';
    BPOT2130_WS_APP_INFO WS_APP_INFO = new BPOT2130_WS_APP_INFO();
    int WS_APP_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSISTO BPCSISTO = new BPCSISTO();
    BPCPQMOV BPCPQMOV = new BPCPQMOV();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPRALIB BPRALIB = new BPRALIB();
    BPRAPAR BPRAPAR = new BPRAPAR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2120_AWA_2120 BPB2120_AWA_2120;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        for (WS_CNT1 = 1; WS_CNT1 <= 101 
            && JIBS_tmp_str[0].trim().length() != 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        }
        CEP.TRC(SCCGWA, WS_CNT1);
        if (WS_CNT1 > 101) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR169);
        }
        CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[1-1]);
        CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[2-1]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT2-1]);
        for (WS_CNT2 = 1; WS_CNT2 <= 200 
            && (JIBS_tmp_str[0].trim().length() != 0 
            || WS_CNT2 <= 1); WS_CNT2 += 1) {
            CEP.TRC(SCCGWA, WS_CNT2);
            if (WS_CNT1 == 1) {
                IBS.init(SCCGWA, BPRALIB);
                BPRALIB.CONF_NO = BPB2120_AWA_2120.CONF_NO;
                CEP.TRC(SCCGWA, BPRALIB.CONF_NO);
                T000_READ1_BPTALIB();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR154);
                }
            }
            if (WS_CNT1 > 2) {
                A000_REDF_AWA();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT2-1]);
            WS_APP_NO = Integer.parseInt(JIBS_tmp_str[0]);
            CEP.TRC(SCCGWA, WS_CNT2);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT2-1]);
            CEP.TRC(SCCGWA, WS_APP_NO);
            B000_MAIN_PROC();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPB2120_AWA_2120.APP_NO_G, WS_APP_INFO);
            CEP.TRC(SCCGWA, WS_CNT2);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[1-1]);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[2-1]);
        }
        CEP.TRC(SCCGWA, "BPOT2130 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2120_AWA_2120>");
        BPB2120_AWA_2120 = (BPB2120_AWA_2120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSISTO);
        IBS.CPY2CLS(SCCGWA, BPB2120_AWA_2120.APP_NO_G, WS_APP_INFO);
        CEP.TRC(SCCGWA, WS_APP_INFO);
    }
    public void A000_REDF_AWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        IBS.init(SCCGWA, BPRAPAR);
        BPRALIB.KEY.APP_NO = WS_APP_INFO.WS_APP_G[WS_CNT2-1].WS_APP_N;
        BPRAPAR.KEY.APP_NO = WS_APP_INFO.WS_APP_G[WS_CNT2-1].WS_APP_N;
        T000_READ_BPTALIB();
        if (pgmRtn) return;
        T000_READ_BPTAPAR();
        if (pgmRtn) return;
        BPB2120_AWA_2120.MOVE_DT = BPRALIB.OUT_DT;
        BPB2120_AWA_2120.CONF_NO = BPRALIB.CONF_NO;
        if (BPRALIB.APP_TYPE == '0') {
            BPB2120_AWA_2120.OUT_BR = BPRALIB.UP_BR;
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                BPB2120_AWA_2120.OUT_BR = BPRALIB.APP_BR;
            }
        }
        BPB2120_AWA_2120.OUT_TLR = BPRALIB.OUT_TLR;
        BPB2120_AWA_2120.CS_KIND = '0';
        BPB2120_AWA_2120.CCY_INFO[1-1].CASH_TYP = "01";
        BPB2120_AWA_2120.CCY_INFO[1-1].CCY = BPRAPAR.APP_CCY;
        BPB2120_AWA_2120.CCY_INFO[1-1].CCY_AMT = BPRAPAR.OUT_AMT;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CS_KIND);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.IN_BR);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CONF_NO);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.MOVE_DT);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.IN_TLR);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.BV_DATE);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.BV_NO);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1]);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[2-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.P_INFO[1-1].P_PVAL);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.P_INFO[1-1].P_NUM);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.P_INFO[1-1].P_MFLG);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.P_INFO[2-1].P_PVAL);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.P_INFO[2-1].P_NUM);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.P_INFO[2-1].P_MFLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CHECK_ORG();
            if (pgmRtn) return;
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_TELLER_FOR_CN();
                if (pgmRtn) return;
            }
            if (WS_APP_NO == 0 
                || WS_APP_NO == ' ') {
                B030_HISTORY_RECORD();
                if (pgmRtn) return;
            }
            B040_IN_STORE_PROCESS_FOR_CN();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CHECK_ORG();
            if (pgmRtn) return;
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_TELLER();
                if (pgmRtn) return;
            }
            B040_IN_STORE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[3-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[4-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[5-1].CCY);
        if (WS_APP_NO != 0 
            && WS_APP_NO != ' ') {
            R000_CHECK_APP_INFO();
            if (pgmRtn) return;
        }
        WS_CS_KIND = BPB2120_AWA_2120.CS_KIND;
        B010_03_CHECK_CCY_INFO();
        if (pgmRtn) return;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 2;
                B010_01_CHECK_DETAILS_BESEQ();
                if (pgmRtn) return;
                WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
                B010_02_CHECK_DETAILS_SYNCHRO();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P905";
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void R000_CHECK_APP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        IBS.init(SCCGWA, BPRAPAR);
        BPRALIB.KEY.APP_NO = WS_APP_NO;
        BPRAPAR.KEY.APP_NO = WS_APP_NO;
        T000_READ_BPTALIB();
        if (pgmRtn) return;
        T000_READ_BPTAPAR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRALIB.APP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPRALIB.APP_TYPE);
        CEP.TRC(SCCGWA, BPRALIB.APP_TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        WS_APP_TYPE = BPRALIB.APP_TYPE;
        if (BPRALIB.APP_TYPE == '0') {
            if (BPRALIB.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR41);
            }
            if (BPB2120_AWA_2120.CCY_INFO[1-1].CCY_AMT != BPRAPAR.OUT_AMT) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR40);
            }
            if (BPRALIB.APP_STS != '5') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR46);
            }
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR38);
                }
                if (BPRALIB.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR114);
                }
                if (BPRALIB.ADT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR115);
                }
                if (BPB2120_AWA_2120.CCY_INFO[1-1].CCY_AMT != BPRAPAR.APP_AMT) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR43);
                }
                if (BPRALIB.APP_STS != '4') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR48);
                }
            }
        }
        CEP.TRC(SCCGWA, BPRALIB.CONF_NO);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CONF_NO);
        CEP.TRC(SCCGWA, BPRALIB.APP_STS);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPRAPAR.APP_CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPRAPAR.OUT_AMT);
        if (BPRALIB.CONF_NO != BPB2120_AWA_2120.CONF_NO) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR49);
        }
        if (!BPB2120_AWA_2120.CCY_INFO[1-1].CCY.equalsIgnoreCase(BPRAPAR.APP_CCY)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR44);
        }
    }
    public void T000_READ_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        BPTALIB_RD.where = "APP_NO = :BPRALIB.KEY.APP_NO";
        BPTALIB_RD.upd = true;
        IBS.READ(SCCGWA, BPRALIB, this, BPTALIB_RD);
    }
    public void T000_READ1_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        BPTALIB_RD.where = "CONF_NO = :BPRALIB.CONF_NO";
        BPTALIB_RD.upd = true;
        BPTALIB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRALIB, this, BPTALIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTALIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTAPAR() throws IOException,SQLException,Exception {
        BPTAPAR_RD = new DBParm();
        BPTAPAR_RD.TableName = "BPTAPAR";
        BPTAPAR_RD.where = "APP_NO = :BPRAPAR.KEY.APP_NO";
        BPTAPAR_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPAR, this, BPTAPAR_RD);
    }
    public void B010_01_CHECK_DETAILS_BESEQ() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ); WS_INFO_CNT += 1) {
            if (BPB2120_AWA_2120.P_INFO[WS_INFO_CNT - 1-1].P_PVAL == 0 
                && BPB2120_AWA_2120.P_INFO[WS_INFO_CNT - 1-1].P_NUM == 0 
                && BPB2120_AWA_2120.P_INFO[WS_INFO_CNT - 1-1].P_MFLG == ' ') {
                if (BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
                if (BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_NUM != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B010_02_CHECK_DETAILS_SYNCHRO() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ); WS_INFO_CNT += 1) {
            if (BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL != 0 
                || BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_NUM != 0 
                || BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                if (BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, WS_INDEX);
                    CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_INDEX-1].CCY);
                    if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2120_AWA_2120.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2120_AWA_2120.CCY_INFO[WS_INDEX-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT - 1-1].CASH_TYP.equalsIgnoreCase("0") 
                && !BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase("0")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT - 1-1].CCY_AMT == 0 
                && BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
                if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
            }
            if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_TELLER_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPQBOX.DATA_INFO.CCY = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2' 
                || BPRTLVB.PLBOX_TP == '5') {
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                WS_CNT = 1001;
                CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
                if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        S000_CALL_BPZPQBOX();
        if (pgmRtn) return;
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQMOV);
        BPCPQMOV.DATA_INFO.MOV_DT = BPB2120_AWA_2120.MOVE_DT;
        BPCPQMOV.DATA_INFO.CONF_NO = BPB2120_AWA_2120.CONF_NO;
        BPCPQMOV.DATA_INFO.CASH_TYP = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQMOV.DATA_INFO.CCY = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQMOV();
        if (pgmRtn) return;
        if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CS_KIND);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CS_KIND);
        if (BPB2120_AWA_2120.CS_KIND != BPCPQMOV.DATA_INFO.CS_KIND) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.CS_KIND_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.OUT_BR);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.OUT_BR);
        if (BPB2120_AWA_2120.OUT_BR != BPCPQMOV.DATA_INFO.OUT_BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.OUT_BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.OUT_TLR);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.OUT_TLR);
        if (!BPB2120_AWA_2120.OUT_TLR.equalsIgnoreCase(BPCPQMOV.DATA_INFO.OUT_TLR)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_BR);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.AMT);
        if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        WS_COUNT = 0;
        WS_GD_AMT = 0;
        WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
        if (WS_CS_KIND == '3') {
            WS_GD_AMT = WS_GD_AMT / 2;
        }
        CEP.TRC(SCCGWA, "AMOUNT:");
        CEP.TRC(SCCGWA, WS_GD_AMT);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCSISTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPQBOX.DATA_INFO.CCY = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2') {
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                WS_CNT = 1001;
                CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
                if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        S000_CALL_BPZPQBOX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.MGR_TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            CEP.TRC(SCCGWA, "1111");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQMOV);
        BPCPQMOV.DATA_INFO.MOV_DT = BPB2120_AWA_2120.MOVE_DT;
        BPCPQMOV.DATA_INFO.CONF_NO = BPB2120_AWA_2120.CONF_NO;
        BPCPQMOV.DATA_INFO.CASH_TYP = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQMOV.DATA_INFO.CCY = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_DT);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CONF_NO);
        S000_CALL_BPZPQMOV();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_STS);
        if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB2120_AWA_2120.CS_KIND != BPCPQMOV.DATA_INFO.CS_KIND) {
            CEP.TRC(SCCGWA, "11");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.CS_KIND_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB2120_AWA_2120.OUT_BR != BPCPQMOV.DATA_INFO.OUT_BR) {
            CEP.TRC(SCCGWA, "22");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.OUT_BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (!BPB2120_AWA_2120.OUT_TLR.equalsIgnoreCase(BPCPQMOV.DATA_INFO.OUT_TLR)) {
            CEP.TRC(SCCGWA, "33");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.OUT_TLR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_TLR);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_BR);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
            CEP.TRC(SCCGWA, "GWA-TR-BRANCH NOT = PQMOV-IN-BR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
            CEP.TRC(SCCGWA, "AWA-CCY-AMT(WS-CCY-CNT) NOT = PQMOV-AMT");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        WS_COUNT = 0;
        WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ) 
            && BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            WS_MATCH_FLAG = 'F';
            for (WS_J = 1; WS_J <= 20 
                && WS_MATCH_FLAG != 'T'; WS_J += 1) {
                CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.PAR_INFO[WS_J-1].PAR_VAL);
                CEP.TRC(SCCGWA, BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL);
                CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.PAR_INFO[WS_J-1].PAR_MFLG);
                CEP.TRC(SCCGWA, BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_MFLG);
                if (BPCPQMOV.DATA_INFO.PAR_INFO[WS_J-1].PAR_VAL == BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL 
                    && BPCPQMOV.DATA_INFO.PAR_INFO[WS_J-1].PAR_MFLG == BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_MFLG) {
                    WS_MATCH_FLAG = 'T';
                    WS_COUNT = WS_COUNT + 1;
                    if (BPCPQMOV.DATA_INFO.PAR_INFO[WS_J-1].PAR_NUM != BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_NUM) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                        WS_FLD_NO = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                        if (pgmRtn) return;
                    }
                }
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            WS_I = WS_INFO_CNT - WS_START_CNT + 1;
            BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_VAL = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_NUM = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_MFLG = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_MFLG;
            if (WS_MATCH_FLAG == 'F') {
                CEP.TRC(SCCGWA, "WS-MATCH-FALSE");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_COUNT != WS_INFO_CNT - ( WS_CCY_CNT - 1 ) * 12 - 1) {
            CEP.TRC(SCCGWA, " WS-COUNT NOT = WS-INFO-CNT - (WS-CCY-CNT - 1) * 12 - 1");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCSISTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
    }
    public void B040_IN_STORE_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        BPCSISTO.MOV_DT = BPB2120_AWA_2120.MOVE_DT;
        BPCSISTO.CONF_NO = BPB2120_AWA_2120.CONF_NO;
        BPCSISTO.OUT_BR = BPB2120_AWA_2120.OUT_BR;
        BPCSISTO.OUT_TLR = BPCPQMOV.DATA_INFO.OUT_TLR;
        BPCSISTO.BV_DATE = BPB2120_AWA_2120.BV_DATE;
        BPCSISTO.BV_NO = BPB2120_AWA_2120.BV_NO;
        BPCSISTO.CS_KIND = BPB2120_AWA_2120.CS_KIND;
        BPCSISTO.PLBOX_NO = BPCPQBOX.DATA_INFO.PLBOX_NO;
        BPCSISTO.APP_NO = WS_APP_NO;
        BPCSISTO.APP_TYPE = WS_APP_TYPE;
        BPCSISTO.CNT = WS_CNT1;
        CEP.TRC(SCCGWA, BPCSISTO.CNT);
        CEP.TRC(SCCGWA, BPCSISTO.APP_NO);
        CEP.TRC(SCCGWA, BPCSISTO.APP_TYPE);
        S000_CALL_BPZSISTO();
        if (pgmRtn) return;
    }
    public void B040_IN_STORE_PROCESS() throws IOException,SQLException,Exception {
        BPCSISTO.MOV_DT = BPB2120_AWA_2120.MOVE_DT;
        BPCSISTO.CONF_NO = BPB2120_AWA_2120.CONF_NO;
        BPCSISTO.OUT_BR = BPB2120_AWA_2120.OUT_BR;
        BPCSISTO.OUT_TLR = BPB2120_AWA_2120.OUT_TLR;
        BPCSISTO.BV_DATE = BPB2120_AWA_2120.BV_DATE;
        BPCSISTO.BV_NO = BPB2120_AWA_2120.BV_NO;
        BPCSISTO.CS_KIND = BPB2120_AWA_2120.CS_KIND;
        S000_CALL_BPZSISTO();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSISTO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_IN_STORE;
        SCCCALL.COMMAREA_PTR = BPCSISTO;
        SCCCALL.ERR_FLDNO = BPB2120_AWA_2120.OUT_BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_MOVD, BPCPQMOV);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCPQMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQMOV.RC);
            WS_FLD_NO = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
