package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLOSS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_TBL_LOSS = "BPTLOSS ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_LOSS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRLOSS BPCRLOSS = new BPCRLOSS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    SCCGWA SCCGWA;
    BPCSLOSS BPCSLOSS;
    public void MP(SCCGWA SCCGWA, BPCSLOSS BPCSLOSS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSLOSS = BPCSLOSS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSLOSS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLOSS.RC);
        IBS.init(SCCGWA, BPCRLOSS);
        IBS.init(SCCGWA, BPRLOSS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSLOSS.FUNC);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.AC);
        CEP.TRC(SCCGWA, BPCSLOSS.PER_FLG);
        CEP.TRC(SCCGWA, BPCSLOSS.AC_TYPE);
        CEP.TRC(SCCGWA, BPCSLOSS.STS);
        CEP.TRC(SCCGWA, BPCSLOSS.BV_TYP);
        CEP.TRC(SCCGWA, BPCSLOSS.BV_CODE);
        CEP.TRC(SCCGWA, BPCSLOSS.BV_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.NEW_BV_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.ID_TYP);
        CEP.TRC(SCCGWA, BPCSLOSS.ID_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.CI_CNM);
        CEP.TRC(SCCGWA, BPCSLOSS.BILL_TYP);
        CEP.TRC(SCCGWA, BPCSLOSS.BILL_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.SUP_BILL_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.BILL_STS);
        CEP.TRC(SCCGWA, BPCSLOSS.BILL_BR);
        CEP.TRC(SCCGWA, BPCSLOSS.OPEN_BR);
        CEP.TRC(SCCGWA, BPCSLOSS.OPEN_AMT);
        CEP.TRC(SCCGWA, BPCSLOSS.OPEN_DT);
        CEP.TRC(SCCGWA, BPCSLOSS.GET_NM);
        CEP.TRC(SCCGWA, BPCSLOSS.GET_AC_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.GET_BR);
        CEP.TRC(SCCGWA, BPCSLOSS.DEP_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_RMK);
        CEP.TRC(SCCGWA, BPCSLOSS.OTH_NM);
        CEP.TRC(SCCGWA, BPCSLOSS.OTH_ID_TYP);
        CEP.TRC(SCCGWA, BPCSLOSS.OTH_ID_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_NM);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_ID_TYP);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_ID_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_ORG);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_TLR);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_DT);
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_TIME);
        CEP.TRC(SCCGWA, BPCSLOSS.HLD_FLG);
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_ORG);
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_TLR);
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_DT);
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_TIME);
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_DUE_TIME);
        if (BPCSLOSS.FUNC == 'C') {
            if (BPCSLOSS.STS == ' ' 
                || BPCSLOSS.LOS_NO.trim().length() == 0) {
                CEP.TRC(SCCGWA, "111");
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            } else {
                if (BPCSLOSS.STS == '1' 
                    || BPCSLOSS.STS == '2') {
                    if (BPCSLOSS.AC.trim().length() == 0 
                        || BPCSLOSS.PER_FLG == ' ') {
                        CEP.TRC(SCCGWA, "222");
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                    }
                }
                if (BPCSLOSS.STS == '2') {
                    if (BPCSLOSS.RLOS_DUE_TIME == 0) {
                        CEP.TRC(SCCGWA, "333");
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                    }
                }
            }
            if (BPCSLOSS.BILL_NO.trim().length() == 0) {
                if (BPCSLOSS.BV_TYP == ' ') {
                    CEP.TRC(SCCGWA, "444");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                } else {
                    if (BPCSLOSS.BV_TYP == '4' 
                        || BPCSLOSS.BV_TYP == '5' 
                        || BPCSLOSS.BV_TYP == '6' 
                        || BPCSLOSS.BV_TYP == '7') {
                        if (BPCSLOSS.BV_NO.trim().length() == 0) {
                            CEP.TRC(SCCGWA, "555");
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                        }
                    }
                }
            } else {
                if (BPCSLOSS.BILL_NO.trim().length() == 0) {
                    CEP.TRC(SCCGWA, "666");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                }
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSLOSS.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSLOSS.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSLOSS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRLOSS);
        BPCRLOSS.INFO.FUNC = 'C';
        S000_CALL_BPZRLOSS();
        if (pgmRtn) return;
        if (BPCRLOSS.RETURN_INFO == 'D') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST);
        }
        CEP.TRC(SCCGWA, BPRLOSS.KEY.LOS_NO);
        CEP.TRC(SCCGWA, BPRLOSS.AC);
        CEP.TRC(SCCGWA, BPRLOSS.PER_FLG);
        CEP.TRC(SCCGWA, BPRLOSS.AC_TYPE);
        CEP.TRC(SCCGWA, BPRLOSS.STS);
        CEP.TRC(SCCGWA, BPRLOSS.BV_TYP);
        CEP.TRC(SCCGWA, BPRLOSS.BV_CODE);
        CEP.TRC(SCCGWA, BPRLOSS.BV_NO);
        CEP.TRC(SCCGWA, BPRLOSS.NEW_BV_NO);
        CEP.TRC(SCCGWA, BPRLOSS.ID_TYP);
        CEP.TRC(SCCGWA, BPRLOSS.ID_NO);
        CEP.TRC(SCCGWA, BPRLOSS.CI_CNM);
        CEP.TRC(SCCGWA, BPRLOSS.BILL_TYP);
        CEP.TRC(SCCGWA, BPRLOSS.BILL_NO);
        CEP.TRC(SCCGWA, BPRLOSS.SUP_BILL_NO);
        CEP.TRC(SCCGWA, BPRLOSS.BILL_STS);
        CEP.TRC(SCCGWA, BPRLOSS.BILL_BR);
        CEP.TRC(SCCGWA, BPRLOSS.OPEN_BR);
        CEP.TRC(SCCGWA, BPRLOSS.OPEN_AMT);
        CEP.TRC(SCCGWA, BPRLOSS.OPEN_DT);
        CEP.TRC(SCCGWA, BPRLOSS.GET_NM);
        CEP.TRC(SCCGWA, BPRLOSS.GET_AC_NO);
        CEP.TRC(SCCGWA, BPRLOSS.GET_BR);
        CEP.TRC(SCCGWA, BPRLOSS.DEP_NO);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_RMK);
        CEP.TRC(SCCGWA, BPRLOSS.OTH_NM);
        CEP.TRC(SCCGWA, BPRLOSS.OTH_ID_TYP);
        CEP.TRC(SCCGWA, BPRLOSS.OTH_ID_NO);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_NM);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_ID_TYP);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_ID_NO);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_ORG);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_ORG);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_TLR);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_TLR);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_DT);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_DT);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_TIME);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_TIME);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_ORG);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_ORG);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_TLR);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_TLR);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_DT);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_DT);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_TIME);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_TIME);
        CEP.TRC(SCCGWA, BPRLOSS.HLD_FLG);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_ORG);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_TLR);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_DT);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_TIME);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_DUE_TIME);
        CEP.TRC(SCCGWA, BPRLOSS.CREATE_DT);
        CEP.TRC(SCCGWA, BPRLOSS.CREATE_TLR);
        CEP.TRC(SCCGWA, BPRLOSS.OWNER_BK);
        CEP.TRC(SCCGWA, BPRLOSS.LAST_UPD_DT);
        CEP.TRC(SCCGWA, BPRLOSS.LAST_UPD_TLR);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_TELE);
        CEP.TRC(SCCGWA, BPRLOSS.LOS_ADDR);
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPRLOSS.KEY.LOS_NO = BPCSLOSS.LOS_NO;
        IBS.init(SCCGWA, BPCRLOSS);
        BPCRLOSS.INFO.FUNC = 'R';
        S000_CALL_BPZRLOSS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, BPCSLOSS.STS);
            CEP.TRC(SCCGWA, BPRLOSS.STS);
            if (BPRLOSS.STS == '6') {
                BPRLOSS.STS = '1';
            }
            CEP.TRC(SCCGWA, BPRLOSS.STS);
        } else {
            CEP.TRC(SCCGWA, BPCSLOSS.NEW_BV_NO);
            if (BPCSLOSS.NEW_BV_NO.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPCSLOSS.NEW_BV_NO);
                BPRLOSS.NEW_BV_NO = BPCSLOSS.NEW_BV_NO;
            }
            CEP.TRC(SCCGWA, BPCSLOSS.STS);
            BPRLOSS.STS = BPCSLOSS.STS;
            if (BPCSLOSS.STS == '3' 
                || BPCSLOSS.STS == '4' 
                || BPCSLOSS.STS == '5' 
                || BPCSLOSS.STS == '6') {
                BPRLOSS.RLOS_ORG = BPCSLOSS.RLOS_ORG;
                BPRLOSS.RLOS_TLR = BPCSLOSS.RLOS_TLR;
                if (BPCSLOSS.RLOS_DT == 0) {
                    BPRLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    BPRLOSS.RLOS_DT = BPCSLOSS.RLOS_DT;
                }
                if (BPCSLOSS.RLOS_TIME == 0) {
                    BPRLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
                } else {
                    BPRLOSS.RLOS_TIME = BPCSLOSS.RLOS_TIME;
                }
                if (BPCSLOSS.RLOS_NM.trim().length() > 0) {
                    BPRLOSS.RLOS_NM = BPCSLOSS.RLOS_NM;
                }
                if (BPCSLOSS.RLOS_ID_TYP.trim().length() > 0) {
                    BPRLOSS.RLOS_ID_TYP = BPCSLOSS.RLOS_ID_TYP;
                }
                if (BPCSLOSS.RLOS_ID_NO.trim().length() > 0) {
                    BPRLOSS.RLOS_ID_NO = BPCSLOSS.RLOS_ID_NO;
                }
                if (BPCSLOSS.RLOS_TELE.trim().length() > 0) {
                    BPRLOSS.RLOS_TELE = BPCSLOSS.RLOS_TELE;
                }
            }
        }
        BPRLOSS.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRLOSS.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCRLOSS);
        BPCRLOSS.INFO.FUNC = 'U';
        S000_CALL_BPZRLOSS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRLOSS.KEY.LOS_NO);
        CEP.TRC(SCCGWA, BPRLOSS.NEW_BV_NO);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_ORG);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_TLR);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_DT);
        CEP.TRC(SCCGWA, BPRLOSS.RLOS_TIME);
        CEP.TRC(SCCGWA, BPRLOSS.LAST_UPD_DT);
        CEP.TRC(SCCGWA, BPRLOSS.LAST_UPD_TLR);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRLOSS.KEY.LOS_NO = BPCSLOSS.LOS_NO;
        BPRLOSS.AC = BPCSLOSS.AC;
        BPRLOSS.PER_FLG = BPCSLOSS.PER_FLG;
        BPRLOSS.AC_TYPE = BPCSLOSS.AC_TYPE;
        BPRLOSS.STS = BPCSLOSS.STS;
        BPRLOSS.BV_TYP = BPCSLOSS.BV_TYP;
        BPRLOSS.BV_CODE = BPCSLOSS.BV_CODE;
        BPRLOSS.BV_NO = BPCSLOSS.BV_NO;
        BPRLOSS.NEW_BV_NO = BPCSLOSS.NEW_BV_NO;
        BPRLOSS.ID_TYP = BPCSLOSS.ID_TYP;
        BPRLOSS.ID_NO = BPCSLOSS.ID_NO;
        BPRLOSS.CI_CNM = BPCSLOSS.CI_CNM;
        BPRLOSS.BILL_TYP = BPCSLOSS.BILL_TYP;
        BPRLOSS.BILL_NO = BPCSLOSS.BILL_NO;
        BPRLOSS.SUP_BILL_NO = BPCSLOSS.SUP_BILL_NO;
        BPRLOSS.BILL_STS = BPCSLOSS.BILL_STS;
        BPRLOSS.BILL_BR = BPCSLOSS.BILL_BR;
        BPRLOSS.OPEN_BR = BPCSLOSS.OPEN_BR;
        BPRLOSS.OPEN_AMT = BPCSLOSS.OPEN_AMT;
        BPRLOSS.OPEN_DT = BPCSLOSS.OPEN_DT;
        BPRLOSS.GET_NM = BPCSLOSS.GET_NM;
        BPRLOSS.GET_AC_NO = BPCSLOSS.GET_AC_NO;
        BPRLOSS.GET_BR = BPCSLOSS.GET_BR;
        BPRLOSS.DEP_NO = BPCSLOSS.DEP_NO;
        BPRLOSS.LOS_RMK = BPCSLOSS.LOS_RMK;
        BPRLOSS.OTH_NM = BPCSLOSS.OTH_NM;
        BPRLOSS.OTH_ID_TYP = BPCSLOSS.OTH_ID_TYP;
        BPRLOSS.OTH_ID_NO = BPCSLOSS.OTH_ID_NO;
        BPRLOSS.LOS_NM = BPCSLOSS.LOS_NM;
        BPRLOSS.LOS_ID_TYP = BPCSLOSS.LOS_ID_TYP;
        BPRLOSS.LOS_ID_NO = BPCSLOSS.LOS_ID_NO;
        if (BPCSLOSS.STS == '1' 
            || BPCSLOSS.STS == '2') {
            if (BPCSLOSS.LOS_ORG == 0) {
                BPRLOSS.LOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            } else {
                BPRLOSS.LOS_ORG = BPCSLOSS.LOS_ORG;
            }
            if (BPCSLOSS.LOS_TLR.trim().length() == 0) {
                BPRLOSS.LOS_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                BPRLOSS.LOS_TLR = BPCSLOSS.LOS_TLR;
            }
            if (BPCSLOSS.LOS_DT == 0) {
                BPRLOSS.LOS_DT = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPRLOSS.LOS_DT = BPCSLOSS.LOS_DT;
            }
            if (BPCSLOSS.LOS_TIME == 0) {
                BPRLOSS.LOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
            } else {
                BPRLOSS.LOS_TIME = BPCSLOSS.LOS_TIME;
            }
        }
        if (BPCSLOSS.STS == '3' 
            || BPCSLOSS.STS == '4' 
            || BPCSLOSS.STS == '5' 
            || BPCSLOSS.STS == '6') {
            if (BPCSLOSS.RLOS_ORG == 0) {
                BPRLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            } else {
                BPRLOSS.RLOS_ORG = BPCSLOSS.RLOS_ORG;
            }
            if (BPCSLOSS.RLOS_TLR.trim().length() == 0) {
                BPRLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                BPRLOSS.RLOS_TLR = BPCSLOSS.RLOS_TLR;
            }
            if (BPCSLOSS.RLOS_DT == 0) {
                BPRLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPRLOSS.RLOS_DT = BPCSLOSS.RLOS_DT;
            }
            if (BPCSLOSS.RLOS_TIME == 0) {
                BPRLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
            } else {
                BPRLOSS.RLOS_TIME = BPCSLOSS.RLOS_TIME;
            }
        }
        BPRLOSS.HLD_FLG = BPCSLOSS.HLD_FLG;
        BPRLOSS.RLOS_ORG = BPCSLOSS.RLOS_ORG;
        BPRLOSS.RLOS_TLR = BPCSLOSS.RLOS_TLR;
        BPRLOSS.RLOS_DT = BPCSLOSS.RLOS_DT;
        BPRLOSS.RLOS_TIME = BPCSLOSS.RLOS_TIME;
        BPRLOSS.RLOS_DUE_TIME = BPCSLOSS.RLOS_DUE_TIME;
        BPRLOSS.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRLOSS.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRLOSS.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRLOSS.LAST_UPD_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRLOSS.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRLOSS.LOS_TELE = BPCSLOSS.LOS_TELE;
        BPRLOSS.LOS_ADDR = BPCSLOSS.LOS_ADDR;
        BPRLOSS.OTH_TELE = BPCSLOSS.OTH_TELE;
        BPRLOSS.RLOS_NM = BPCSLOSS.RLOS_NM;
        BPRLOSS.RLOS_ID_TYP = BPCSLOSS.RLOS_ID_TYP;
        BPRLOSS.RLOS_ID_NO = BPCSLOSS.RLOS_ID_NO;
        BPRLOSS.RLOS_TELE = BPCSLOSS.RLOS_TELE;
    }
    public void S000_CALL_BPZRLOSS() throws IOException,SQLException,Exception {
        BPCRLOSS.INFO.POINTER = BPRLOSS;
        BPCRLOSS.INFO.REC_LEN = 2542;
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-LOSS-INFO", BPCRLOSS);
        if (BPCRLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRLOSS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSLOSS.RC);
            CEP.ERR(SCCGWA, BPCRLOSS.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCSLOSS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCSLOSS = ");
            CEP.TRC(SCCGWA, BPCSLOSS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
