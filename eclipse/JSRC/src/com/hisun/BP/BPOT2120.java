package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2120 {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTALIB_RD;
    DBParm BPTAPAR_RD;
    String S_OUT_STORE = "BP-S-OUT-STORE      ";
    String P_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE   ";
    String S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String P_INQ_ORG = "BP-P-INQ-ORG        ";
    String P_Q_CBOX = "BP-P-Q-CBOX         ";
    String R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String R_ADW_TLVB = "BP-R-ADW-TLVB";
    String HIS_REMARKS = "BR-CS-MOV-OUT";
    BPOT2120_WS_VARIABLES WS_VARIABLES = new BPOT2120_WS_VARIABLES();
    int WS_CNT1 = 0;
    int WS_CNT2 = 0;
    BPOT2120_WS_COND_FLG WS_COND_FLG = new BPOT2120_WS_COND_FLG();
    BPOT2120_WS_CONF_INFO WS_CONF_INFO = new BPOT2120_WS_CONF_INFO();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSOSTO BPCSOSTO = new BPCSOSTO();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCPQORR BPCPQORR = new BPCPQORR();
    BPRALIB BPRALIB = new BPRALIB();
    BPRAPAR BPRAPAR = new BPRAPAR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2120_AWA_2120 AWA_2120;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_COND_FLG.WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        for (WS_CNT1 = 1; WS_CNT1 <= 101 
            && JIBS_tmp_str[0].trim().length() != 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_COND_FLG.WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        }
        IBS.init(SCCGWA, WS_CONF_INFO);
        CEP.TRC(SCCGWA, WS_CNT1);
        if (WS_CNT1 > 101) {
            CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR169);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_COND_FLG.WS_APP_INFO.WS_APP_G[WS_CNT2-1]);
        for (WS_CNT2 = 1; WS_CNT2 <= 200 
            && (JIBS_tmp_str[0].trim().length() != 0 
            || WS_CNT2 <= 1); WS_CNT2 += 1) {
            CEP.TRC(SCCGWA, WS_CNT2);
            if (WS_CNT1 > 2) {
                A000_REDF_AWA();
            }
            CEP.TRC(SCCGWA, WS_COND_FLG.APP_NO);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_COND_FLG.WS_APP_INFO.WS_APP_G[WS_CNT2-1]);
            WS_COND_FLG.APP_NO = Integer.parseInt(JIBS_tmp_str[0]);
            CEP.TRC(SCCGWA, WS_CNT2);
            CEP.TRC(SCCGWA, WS_COND_FLG.WS_APP_INFO.WS_APP_G[WS_CNT2-1]);
            CEP.TRC(SCCGWA, WS_COND_FLG.APP_NO);
            B000_MAIN_PROC();
            IBS.init(SCCGWA, WS_VARIABLES);
            IBS.CPY2CLS(SCCGWA, AWA_2120.APP_NO_G, WS_COND_FLG.WS_APP_INFO);
        }
        AWA_2120.CONF_NOG = IBS.CLS2CPY(SCCGWA, WS_CONF_INFO);
        CEP.TRC(SCCGWA, "BPOT2120 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_2120 = new BPB2120_AWA_2120();
        IBS.init(SCCGWA, AWA_2120);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_2120);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, BPCSOSTO);
        WS_COND_FLG.CHECK_ORG = 'N';
        IBS.CPY2CLS(SCCGWA, AWA_2120.APP_NO_G, WS_COND_FLG.WS_APP_INFO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B010_CHECK_BR_RELA_CN();
            B020_CHECK_ORG_FOR_CN();
            B050_GET_PLBOX_NO_FOR_CN();
            for (WS_VARIABLES.CCY_CNT = 1; WS_VARIABLES.CCY_CNT <= 5 
                && AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY.trim().length() != 0; WS_VARIABLES.CCY_CNT += 1) {
                B030_CHECK_TELLER_FOR_CN();
            }
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            if (WS_COND_FLG.APP_NO == 0 
                || WS_COND_FLG.APP_NO == ' ') {
                B030_HISTORY_RECORD();
            }
            B040_OUT_STORE_PROCESS_FOR_CN();
        } else {
            B010_CHECK_INPUT();
            B020_CHECK_ORG();
            for (WS_VARIABLES.CCY_CNT = 1; WS_VARIABLES.CCY_CNT <= 5 
                && AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY.trim().length() != 0; WS_VARIABLES.CCY_CNT += 1) {
                B030_CHECK_TELLER();
            }
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            B040_OUT_STORE_PROCESS();
        }
    }
    public void A000_REDF_AWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        IBS.init(SCCGWA, BPRAPAR);
        BPRALIB.KEY.APP_NO = WS_COND_FLG.WS_APP_INFO.WS_APP_G[WS_CNT2-1].APP_N;
        BPRAPAR.KEY.APP_NO = WS_COND_FLG.WS_APP_INFO.WS_APP_G[WS_CNT2-1].APP_N;
        CEP.TRC(SCCGWA, WS_CNT2);
        CEP.TRC(SCCGWA, BPRALIB.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRAPAR.KEY.APP_NO);
        T000_READ_BPTALIB();
        T000_READ_BPTAPAR();
        if (BPRALIB.APP_TYPE == '0') {
            AWA_2120.IN_BR = BPRALIB.APP_BR;
            AWA_2120.CCY_INFO[1-1].CCY_AMT = BPRAPAR.OUT_AMT;
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                AWA_2120.IN_BR = BPRALIB.UP_BR;
                AWA_2120.CCY_INFO[1-1].CCY_AMT = BPRAPAR.APP_AMT;
            }
        }
        AWA_2120.CS_KIND = '0';
        AWA_2120.CCY_INFO[1-1].CASH_TYP = "01";
        AWA_2120.CCY_INFO[1-1].CCY = BPRAPAR.APP_CCY;
        AWA_2120.ALLOT_TP = BPRALIB.ALLOT_TYPE;
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P905";
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B010_CHECK_BR_RELA_CN() throws IOException,SQLException,Exception {
        if (WS_COND_FLG.APP_NO == 0 
            || WS_COND_FLG.APP_NO == ' ') {
            WS_COND_FLG.ALLOT_TYPE = " ";
            WS_COND_FLG.ALLOT_TP = ' ';
            WS_COND_FLG.ALLOT_TP = AWA_2120.ALLOT_TP;
            WS_COND_FLG.ALLOT_TYPE = "07";
        }
        IBS.init(SCCGWA, BPCPQORR);
        BPCPQORR.TYP = WS_COND_FLG.ALLOT_TYPE;
        BPCPQORR.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPCPQORR.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, AWA_2120.IN_BR);
        S000_CALL_BPZPQORR();
        CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
        WS_VARIABLES.IN_BR1 = BPCPQORR.REL_BR;
        if (BPCPQORR.ONWAY_DAY.trim().length() == 0) WS_COND_FLG.ONWAY_DT = 0;
        else WS_COND_FLG.ONWAY_DT = Integer.parseInt(BPCPQORR.ONWAY_DAY);
        CEP.TRC(SCCGWA, WS_VARIABLES.IN_BR1);
        if (WS_VARIABLES.IN_BR1 == AWA_2120.IN_BR) {
            CEP.TRC(SCCGWA, "DEV1");
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.TYP = WS_COND_FLG.ALLOT_TYPE;
            BPCPQORR.BR = AWA_2120.IN_BR;
            CEP.TRC(SCCGWA, AWA_2120.IN_BR);
            S000_CALL_BPZPQORR();
            WS_VARIABLES.IN_BR2 = BPCPQORR.REL_BR;
            if (BPCPQORR.ONWAY_DAY.trim().length() == 0) WS_COND_FLG.ONWAY_DT = 0;
            else WS_COND_FLG.ONWAY_DT = Integer.parseInt(BPCPQORR.ONWAY_DAY);
            CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
            if (WS_VARIABLES.IN_BR2 == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR91);
            }
        } else {
            CEP.TRC(SCCGWA, "DEV2");
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.TYP = WS_COND_FLG.ALLOT_TYPE;
            BPCPQORR.BR = AWA_2120.IN_BR;
            CEP.TRC(SCCGWA, AWA_2120.IN_BR);
            CEP.TRC(SCCGWA, BPCPQORR.TYP);
            S000_CALL_BPZPQORR();
            CEP.TRC(SCCGWA, BPCPQORR.RC);
            if (BPCPQORR.RC.RC_CODE == 0) {
                WS_VARIABLES.IN_BR2 = BPCPQORR.REL_BR;
            }
            CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
            if (BPCPQORR.RC.RC_CODE != 0 
                || WS_VARIABLES.IN_BR2 != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, ERROR_MSG.ORG_HAS_NO_ALLOT_RELAT);
            }
            if (BPCPQORR.ONWAY_DAY.trim().length() == 0) WS_COND_FLG.ONWAY_DT = 0;
            else WS_COND_FLG.ONWAY_DT = Integer.parseInt(BPCPQORR.ONWAY_DAY);
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.ONWAY_DT);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_COND_FLG.APP_NO);
        if (WS_COND_FLG.APP_NO != 0 
            && WS_COND_FLG.APP_NO != ' ') {
            R000_CHECK_APP_INFO();
        }
        WS_COND_FLG.CS_KIND = AWA_2120.CS_KIND;
        B010_03_CHECK_CCY_INFO();
        for (WS_VARIABLES.CCY_CNT = 1; WS_VARIABLES.CCY_CNT <= 5; WS_VARIABLES.CCY_CNT += 1) {
            if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY.trim().length() > 0) {
                WS_VARIABLES.START_CNT = ( WS_VARIABLES.CCY_CNT - 1 ) * 12 + 2;
                WS_VARIABLES.START_CNT = ( WS_VARIABLES.CCY_CNT - 1 ) * 12 + 1;
            }
        }
    }
    public void R000_CHECK_APP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        IBS.init(SCCGWA, BPRAPAR);
        BPRALIB.KEY.APP_NO = WS_COND_FLG.APP_NO;
        BPRAPAR.KEY.APP_NO = WS_COND_FLG.APP_NO;
        T000_READ_BPTALIB();
        T000_READ_BPTAPAR();
        WS_COND_FLG.APP_TYPE = BPRALIB.APP_TYPE;
        WS_COND_FLG.ALLOT_TP = BPRALIB.ALLOT_TYPE;
        WS_COND_FLG.ALLOT_TYPE = "07";
        CEP.TRC(SCCGWA, BPRALIB.APP_TYPE);
        CEP.TRC(SCCGWA, BPRALIB.UP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPRALIB.APP_BR);
        CEP.TRC(SCCGWA, BPRALIB.APP_STS);
        if (BPRALIB.APP_TYPE == '0') {
            if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR38);
            }
            if (BPRALIB.APP_STS != '4') {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR39);
            }
            if (AWA_2120.CCY_INFO[1-1].CCY_AMT != BPRAPAR.OUT_AMT) {
                CEP.TRC(SCCGWA, "DEV2");
                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR40);
            }
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                if (BPRALIB.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR41);
                }
                if (BPRALIB.APP_STS != '0') {
                    CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR42);
                }
                CEP.TRC(SCCGWA, AWA_2120.CCY_INFO[1-1].CCY_AMT);
                CEP.TRC(SCCGWA, BPRAPAR.APP_AMT);
                if (AWA_2120.CCY_INFO[1-1].CCY_AMT != BPRAPAR.APP_AMT) {
                    CEP.TRC(SCCGWA, "DEV3");
                    CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR43);
                }
            }
        }
        if (!AWA_2120.CCY_INFO[1-1].CCY.equalsIgnoreCase(BPRAPAR.APP_CCY)) {
            CEP.TRC(SCCGWA, "DEV1");
            CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR44);
        }
    }
    public void T000_READ_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        BPTALIB_RD.where = "APP_NO = :BPRALIB.KEY.APP_NO";
        BPTALIB_RD.upd = true;
        IBS.READ(SCCGWA, BPRALIB, this, BPTALIB_RD);
    }
    public void T000_READ_BPTAPAR() throws IOException,SQLException,Exception {
        BPTAPAR_RD = new DBParm();
        BPTAPAR_RD.TableName = "BPTAPAR";
        BPTAPAR_RD.where = "APP_NO = :BPRAPAR.KEY.APP_NO";
        BPTAPAR_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPAR, this, BPTAPAR_RD);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = AWA_2120.IN_BR;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_VARIABLES.FLD_NO = (short) AWA_2120.IN_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-REL", BPCPQORR);
    }
    public void B010_01_CHECK_DETAILS_BESEQ() throws IOException,SQLException,Exception {
        for (WS_VARIABLES.INFO_CNT = WS_VARIABLES.START_CNT; WS_VARIABLES.INFO_CNT <= ( WS_VARIABLES.CCY_CNT * 12 ); WS_VARIABLES.INFO_CNT += 1) {
            if (AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT - 1-1].P_PVAL == 0 
                && AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT - 1-1].P_NUM == 0 
                && AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT - 1-1].P_MFLG == ' ') {
                if (AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL != 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_VARIABLES.FLD_NO = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM != 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_VARIABLES.FLD_NO = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_02_CHECK_DETAILS_SYNCHRO() throws IOException,SQLException,Exception {
        for (WS_VARIABLES.INFO_CNT = WS_VARIABLES.START_CNT; WS_VARIABLES.INFO_CNT <= ( WS_VARIABLES.CCY_CNT * 12 ); WS_VARIABLES.INFO_CNT += 1) {
            if (AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL != 0 
                || AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM != 0 
                || AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_MFLG != ' ') {
                if (AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL == 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT;
                    WS_VARIABLES.FLD_NO = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_VARIABLES.CCY_CNT = 2; WS_VARIABLES.CCY_CNT <= 5; WS_VARIABLES.CCY_CNT += 1) {
            if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_VARIABLES.INDEX = 1; WS_VARIABLES.INDEX != WS_VARIABLES.CCY_CNT; WS_VARIABLES.INDEX += 1) {
                    if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY.equalsIgnoreCase(AWA_2120.CCY_INFO[WS_VARIABLES.INDEX-1].CCY) 
                        && AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP.equalsIgnoreCase(AWA_2120.CCY_INFO[WS_VARIABLES.INDEX-1].CASH_TYP)) {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CCY_REPEATED;
                        WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            CEP.TRC(SCCGWA, AWA_2120.CCY_INFO[1-1].CCY);
            CEP.TRC(SCCGWA, BPRAPAR.APP_CCY);
            CEP.TRC(SCCGWA, AWA_2120.CCY_INFO[1-1].CCY_AMT);
            CEP.TRC(SCCGWA, BPRAPAR.OUT_AMT);
            CEP.TRC(SCCGWA, BPRAPAR.APP_AMT);
            if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT - 1-1].CASH_TYP.equalsIgnoreCase("0") 
                && !AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP.equalsIgnoreCase("0")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT - 1-1].CCY.trim().length() == 0 
                && AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY.trim().length() > 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT - 1-1].CCY_AMT == 0 
                && AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_AMT != 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_VARIABLES.CCY_CNT = 1; WS_VARIABLES.CCY_CNT <= 5; WS_VARIABLES.CCY_CNT += 1) {
            if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY.trim().length() > 0) {
                if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_AMT == 0) {
                    CEP.TRC(SCCGWA, "DEV3");
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT;
                    WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP.trim().length() == 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT;
                    WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                WS_VARIABLES.INFO_CNT = ( WS_VARIABLES.CCY_CNT - 1 ) * 12 + 1;
            }
            if (AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_AMT != 0 
                && AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "DEV4");
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MUST_INPUT;
                WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_ORG_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = AWA_2120.IN_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        if (BPCPQORG.ORG_STS != 'O') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_IN_ORG_SIGN_OFF;
            WS_VARIABLES.FLD_NO = AWA_2120.IN_BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (AWA_2120.IN_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ORG_SAME_NOTALLOWED;
            WS_VARIABLES.FLD_NO = AWA_2120.IN_BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = AWA_2120.IN_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        if (BPCPQORG.ORG_STS != 'O') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_IN_ORG_SIGN_OFF;
            WS_VARIABLES.FLD_NO = AWA_2120.IN_BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (AWA_2120.IN_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ORG_SAME_NOTALLOWED;
            WS_VARIABLES.FLD_NO = AWA_2120.IN_BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_TELLER_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_BR);
        BPCPQBOX.DATA_INFO.CCY = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY;
        BPCPQBOX.DATA_INFO.CASH_TYP = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP;
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CCY);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CASH_TYP);
        S000_CALL_BPZPQBOX();
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.MGR_TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.MGR_TLR);
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_CLIB_MGR;
            WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        B060_CHECK_PVAL_ENOUGH_FOR_CN();
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 235;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 235;
        S000_CALL_BPZRTLVB();
        WS_VARIABLES.CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_VARIABLES.CNT <= 1000) {
            WS_VARIABLES.CNT += 1;
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2') {
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                WS_VARIABLES.CNT = 1001;
                if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_CASHLIB_TLR;
                    S000_ERR_MSG_PROC();
                }
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 235;
            S000_CALL_BPZRTLVB();
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 235;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_BR);
        BPCPQBOX.DATA_INFO.CCY = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY;
        BPCPQBOX.DATA_INFO.CASH_TYP = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP;
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CCY);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CASH_TYP);
        S000_CALL_BPZPQBOX();
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.MGR_TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.MGR_TLR);
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_CLIB_MGR;
            WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        WS_VARIABLES.GD_AMT = 0;
        WS_VARIABLES.START_CNT = ( WS_VARIABLES.CCY_CNT - 1 ) * 12 + 1;
        for (WS_VARIABLES.INFO_CNT = WS_VARIABLES.START_CNT; WS_VARIABLES.INFO_CNT <= ( WS_VARIABLES.CCY_CNT * 12 ) 
            && AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL != 0; WS_VARIABLES.INFO_CNT += 1) {
            if (AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM > 0) {
                WS_COND_FLG.MATCH_FLAG = 'F';
                for (WS_VARIABLES.J = 1; WS_VARIABLES.J <= 20 
                    && WS_COND_FLG.MATCH_FLAG != 'T'; WS_VARIABLES.J += 1) {
                    if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].PAR_VAL == AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL 
                        && BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].M_FLG == AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_MFLG) {
                        WS_COND_FLG.MATCH_FLAG = 'T';
                        if (WS_COND_FLG.CS_KIND == '0') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].GD_NUM < AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM) {
                                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                WS_VARIABLES.FLD_NO = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM_NO;
                                S000_ERR_MSG_PROC();
                            }
                        } else if (WS_COND_FLG.CS_KIND == '2') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].BD_NUM < AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM) {
                                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                WS_VARIABLES.FLD_NO = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM_NO;
                                S000_ERR_MSG_PROC();
                            }
                        } else if (WS_COND_FLG.CS_KIND == '3') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].HBD_NUM < AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM) {
                                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                WS_VARIABLES.FLD_NO = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM_NO;
                                S000_ERR_MSG_PROC();
                            }
                        } else {
                        }
                    }
                }
                if (WS_COND_FLG.MATCH_FLAG == 'F') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_THIS_PVAL;
                    WS_VARIABLES.FLD_NO = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_VARIABLES.GD_AMT = WS_VARIABLES.GD_AMT + AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL * AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM;
            if (WS_COND_FLG.CS_KIND == '3') {
                WS_VARIABLES.GD_AMT = WS_VARIABLES.GD_AMT / 2;
            }
            WS_VARIABLES.I = WS_VARIABLES.INFO_CNT - WS_VARIABLES.START_CNT + 1;
            BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_VARIABLES.I-1].CCY_VAL = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL;
            BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_VARIABLES.I-1].CCY_NUM = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM;
            BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_VARIABLES.I-1].CCY_MFLG = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_MFLG;
        }
        BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].TOTAL_AMT = WS_VARIABLES.GD_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.GD_AMT);
        if (WS_VARIABLES.GD_AMT != AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_AMT) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AMT_NOTMATCH_PVAL;
            WS_VARIABLES.FLD_NO = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP;
        BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY;
    }
    public void B040_OUT_STORE_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        BPCSOSTO.CS_KIND = AWA_2120.CS_KIND;
        BPCSOSTO.IN_BR = AWA_2120.IN_BR;
        BPCSOSTO.BV_DATE = AWA_2120.BV_DATE;
        BPCSOSTO.BV_NO = AWA_2120.BV_NO;
        BPCSOSTO.PLBOX_NO = BPCPQBOX.DATA_INFO.PLBOX_NO;
        CEP.TRC(SCCGWA, WS_COND_FLG.APP_NO);
        CEP.TRC(SCCGWA, WS_COND_FLG.APP_TYPE);
        BPCSOSTO.APP_NO = WS_COND_FLG.APP_NO;
        BPCSOSTO.APP_TYPE = WS_COND_FLG.APP_TYPE;
        BPCSOSTO.ONWAY_DT = WS_COND_FLG.ONWAY_DT;
        BPCSOSTO.ALLOT_TYPE = WS_COND_FLG.ALLOT_TP;
        BPCSOSTO.CNT = WS_CNT1;
        CEP.TRC(SCCGWA, BPCSOSTO.CNT);
        S000_CALL_BPZSOSTO();
        AWA_2120.MOVE_DT = BPCSOSTO.MOVE_DT;
        WS_CONF_INFO.WS_CONF_G[WS_CNT2-1].CONF_N = BPCSOSTO.CONF_NO;
        CEP.TRC(SCCGWA, WS_CNT2);
        CEP.TRC(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT2-1].CONF_N);
        AWA_2120.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        AWA_2120.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void B040_OUT_STORE_PROCESS() throws IOException,SQLException,Exception {
        BPCSOSTO.CS_KIND = AWA_2120.CS_KIND;
        BPCSOSTO.IN_BR = AWA_2120.IN_BR;
        BPCSOSTO.BV_DATE = AWA_2120.BV_DATE;
        BPCSOSTO.BV_NO = AWA_2120.BV_NO;
        S000_CALL_BPZSOSTO();
        AWA_2120.MOVE_DT = BPCSOSTO.MOVE_DT;
        AWA_2120.CONF_NO = BPCSOSTO.CONF_NO;
        AWA_2120.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        AWA_2120.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void B050_GET_PLBOX_NO_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '1';
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 235;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            BPRTLVB.PLBOX_TP = '2';
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 235;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                BPRTLVB.PLBOX_TP = '5';
                BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCTLVBF.INFO.FUNC = 'I';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 235;
                S000_CALL_BPZTLVBF();
                if (BPCTLVBF.RETURN_INFO == 'N') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_CASH_VLT;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B060_CHECK_PVAL_ENOUGH_FOR_CN() throws IOException,SQLException,Exception {
        WS_VARIABLES.GD_AMT = 0;
        WS_VARIABLES.START_CNT = ( WS_VARIABLES.CCY_CNT - 1 ) * 12 + 1;
        for (WS_VARIABLES.INFO_CNT = WS_VARIABLES.START_CNT; WS_VARIABLES.INFO_CNT <= ( WS_VARIABLES.CCY_CNT * 12 ) 
            && AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL != 0; WS_VARIABLES.INFO_CNT += 1) {
            if (BPRTLVB.PLBOX_TP != '5' 
                && AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM > 0) {
                WS_COND_FLG.MATCH_FLAG = 'F';
                for (WS_VARIABLES.J = 1; WS_VARIABLES.J <= 20 
                    && WS_COND_FLG.MATCH_FLAG != 'T'; WS_VARIABLES.J += 1) {
                    if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].PAR_VAL == AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL 
                        && BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].M_FLG == AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_MFLG) {
                        WS_COND_FLG.MATCH_FLAG = 'T';
                        if (WS_COND_FLG.CS_KIND == '0') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].GD_NUM < AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM) {
                                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                S000_ERR_MSG_PROC();
                            }
                        } else if (WS_COND_FLG.CS_KIND == '2') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].BD_NUM < AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM) {
                                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                S000_ERR_MSG_PROC();
                            }
                        } else if (WS_COND_FLG.CS_KIND == '3') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_VARIABLES.J-1].HBD_NUM < AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM) {
                                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                S000_ERR_MSG_PROC();
                            }
                        } else {
                        }
                    }
                }
                if (WS_COND_FLG.MATCH_FLAG == 'F') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_THIS_PVAL;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_VARIABLES.GD_AMT = WS_VARIABLES.GD_AMT + AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL * AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM;
            if (WS_COND_FLG.CS_KIND == '3') {
                WS_VARIABLES.GD_AMT = WS_VARIABLES.GD_AMT / 2;
            }
            WS_VARIABLES.I = WS_VARIABLES.INFO_CNT - WS_VARIABLES.START_CNT + 1;
            BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_VARIABLES.I-1].CCY_VAL = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_PVAL;
            BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_VARIABLES.I-1].CCY_NUM = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_NUM;
            BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_VARIABLES.I-1].CCY_MFLG = AWA_2120.P_INFO[WS_VARIABLES.INFO_CNT-1].P_MFLG;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.GD_AMT);
        BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].TOTAL_AMT = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY_AMT;
        BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP;
        BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY = AWA_2120.CCY_INFO[WS_VARIABLES.CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, WS_VARIABLES.CCY_CNT);
        CEP.TRC(SCCGWA, BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].TOTAL_AMT);
        CEP.TRC(SCCGWA, BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPCSOSTO.DATA_INFO[WS_VARIABLES.CCY_CNT-1].CCY);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZSOSTO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = S_OUT_STORE;
        SCCCALL.COMMAREA_PTR = BPCSOSTO;
        SCCCALL.ERR_FLDNO = AWA_2120.IN_BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
