package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSOCAC {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTOCAC_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_CI_CIZCUST = "CI-CIZCUST";
    String K_TBL_OCAC = "BPTOCAC ";
    String WS_TEMP_RECORD = " ";
    BPZSOCAC_WS_DATA WS_DATA = new BPZSOCAC_WS_DATA();
    char WS_TBL_OCAC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICCUST CICCUST = new CICCUST();
    BPCROCAC BPCROCAC = new BPCROCAC();
    BPROCAC BPROCAC = new BPROCAC();
    SCCGWA SCCGWA;
    BPCSOCAC BPCSOCAC;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, BPCSOCAC BPCSOCAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSOCAC = BPCSOCAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSOCAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            CEP.TRC(SCCGWA, "OTH");
            CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
            IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
            IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        }
        IBS.init(SCCGWA, WS_DATA);
        IBS.init(SCCGWA, BPCSOCAC.RC);
        IBS.init(SCCGWA, BPCROCAC);
        IBS.init(SCCGWA, BPROCAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSOCAC.FUNC);
        CEP.TRC(SCCGWA, BPCSOCAC.AC);
        CEP.TRC(SCCGWA, BPCSOCAC.ACO_AC);
        CEP.TRC(SCCGWA, BPCSOCAC.STS);
        CEP.TRC(SCCGWA, BPCSOCAC.WORK_TYP);
        CEP.TRC(SCCGWA, BPCSOCAC.CAL_TYP);
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_TYP);
        CEP.TRC(SCCGWA, BPCSOCAC.CI_TYPE);
        CEP.TRC(SCCGWA, BPCSOCAC.BV_TYP);
        CEP.TRC(SCCGWA, BPCSOCAC.BV_NO);
        CEP.TRC(SCCGWA, BPCSOCAC.ID_TYP);
        CEP.TRC(SCCGWA, BPCSOCAC.ID_NO);
        CEP.TRC(SCCGWA, BPCSOCAC.CI_CNM);
        CEP.TRC(SCCGWA, BPCSOCAC.CARD_FLG);
        CEP.TRC(SCCGWA, BPCSOCAC.SEQ);
        CEP.TRC(SCCGWA, BPCSOCAC.CCY);
        CEP.TRC(SCCGWA, BPCSOCAC.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCSOCAC.AUT_TLR);
        CEP.TRC(SCCGWA, BPCSOCAC.OPEN_DATE);
        CEP.TRC(SCCGWA, BPCSOCAC.CLOSE_DATE);
        CEP.TRC(SCCGWA, BPCSOCAC.OPEN_TLR);
        CEP.TRC(SCCGWA, BPCSOCAC.CLOSE_TLR);
        CEP.TRC(SCCGWA, BPCSOCAC.OPEN_NO);
        CEP.TRC(SCCGWA, BPCSOCAC.REOPN_DATE);
        CEP.TRC(SCCGWA, BPCSOCAC.CLOSE_REASON);
        CEP.TRC(SCCGWA, BPCSOCAC.CLOSE_NO);
        CEP.TRC(SCCGWA, BPCSOCAC.CLOSE_AC_STS);
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_RAT);
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_INT);
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_TAX);
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_AMT);
        CEP.TRC(SCCGWA, BPCSOCAC.CHNL_NO);
        CEP.TRC(SCCGWA, BPCSOCAC.OPEN_AMT);
        CEP.TRC(SCCGWA, BPCSOCAC.PROD_CD);
        CEP.TRC(SCCGWA, BPCSOCAC.REMARK);
        CEP.TRC(SCCGWA, BPCSOCAC.OTH_RPT_NM);
        CEP.TRC(SCCGWA, BPCSOCAC.OTH_ID_TYP);
        CEP.TRC(SCCGWA, BPCSOCAC.OTH_ID_NO);
        CEP.TRC(SCCGWA, BPCSOCAC.BR);
        CEP.TRC(SCCGWA, BPCSOCAC.CLO_BR);
        CEP.TRC(SCCGWA, BPCSOCAC.OLD_AC);
        CEP.TRC(SCCGWA, BPCSOCAC.OPEN_RAT);
        CEP.TRC(SCCGWA, BPCSOCAC.AC_CNM);
        CEP.TRC(SCCGWA, BPCSOCAC.NEW_BV_TYP);
        CEP.TRC(SCCGWA, BPCSOCAC.COL_IND);
        if (BPCSOCAC.FUNC == 'C') {
            if (BPCSOCAC.AC.trim().length() == 0 
                || BPCSOCAC.STS == ' ') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            } else {
                if (BPCSOCAC.STS == 'N') {
                    if (BPCSOCAC.CI_TYPE == ' ' 
                        || BPCSOCAC.ID_TYP.trim().length() == 0 
                        || BPCSOCAC.ID_NO.trim().length() == 0 
                        || BPCSOCAC.CI_CNM.trim().length() == 0 
                        || BPCSOCAC.OPEN_DATE == 0 
                        || BPCSOCAC.PROD_CD.trim().length() == 0) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                    }
                } else {
                    if (BPCSOCAC.STS == 'C') {
                        if (BPCSOCAC.CLOSE_DATE == 0) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                        }
                    }
                }
            }
            if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                if (BPCSOCAC.BV_TYP == '7' 
                    && BPCSOCAC.BV_NO.trim().length() == 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                }
            } else {
                if (BPCSOCAC.BV_TYP == '4' 
                    || BPCSOCAC.BV_TYP == '5' 
                    || BPCSOCAC.BV_TYP == '6' 
                    || BPCSOCAC.BV_TYP == '7') {
                    if (BPCSOCAC.BV_NO.trim().length() == 0) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                    }
                }
            }
        }
        if (BPCSOCAC.STS == 'K' 
            || BPCSOCAC.STS == 'D') {
            if (BPCSOCAC.OLD_AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B050_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCSOCAC.FUNC == 'C') {
                IBS.init(SCCGWA, BPROCAC);
                R000_TRANS_DATA_PARAMETER();
                if (pgmRtn) return;
                B010_CREATE_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCSOCAC.FUNC == 'U') {
                if (BPCSOCAC.STS == 'K') {
                    B020_UPDATE_RECORD_PROC();
                    if (pgmRtn) return;
                } else {
                    if (BPCSOCAC.STS == 'D') {
                        B030_UPDATE_RECORD_PROC();
                        if (pgmRtn) return;
                    } else {
                        if (BPCSOCAC.STS == 'B') {
                            B060_UPDATE_RECORD_PROC();
                            if (pgmRtn) return;
                        } else {
                            if (BPCSOCAC.STS == 'R') {
                                B070_UPDATE_RECORD_PROC();
                                if (pgmRtn) return;
                            } else {
                                B040_UPDATE_RECORD_PROC();
                                if (pgmRtn) return;
                            }
                        }
                    }
                }
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSOCAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCROCAC);
        BPCROCAC.INFO.FUNC = 'C';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        if (BPCROCAC.RETURN_INFO == 'D') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST);
        }
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPROCAC);
        T000_STARTBR_BPTOCAC();
        if (pgmRtn) return;
        T000_READNEXT_BPTOCAC();
        if (pgmRtn) return;
        while (WS_TBL_OCAC_FLAG != 'D' 
            && SCCMPAG.FUNC != 'E') {
            R000_READ_DATA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_DATA.WS_AC);
            CEP.TRC(SCCGWA, WS_DATA.WS_ACO_AC);
            BPROCAC.KEY.AC = WS_DATA.WS_AC;
            BPROCAC.KEY.ACO_AC = WS_DATA.WS_ACO_AC;
            IBS.init(SCCGWA, BPCROCAC);
            BPCROCAC.INFO.FUNC = 'R';
            S000_CALL_BPZROCAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "UPDATE BEGIN");
            BPROCAC.STS = 'C';
            BPROCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPROCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPROCAC.CLO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPROCAC.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPROCAC.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBS.init(SCCGWA, BPCROCAC);
            BPCROCAC.INFO.FUNC = 'U';
            S000_CALL_BPZROCAC();
            if (pgmRtn) return;
            R000_MOVES_DATA();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPROCAC);
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            if (BPROCAC.KEY.ACO_AC.trim().length() == 0) {
                CEP.TRC(SCCGWA, BPCSOCAC.NEW_BV_TYP);
                BPROCAC.BV_TYP = BPCSOCAC.NEW_BV_TYP;
                CEP.TRC(SCCGWA, BPCSOCAC.NEW_PROD_CD);
                BPROCAC.PROD_CD = BPCSOCAC.NEW_PROD_CD;
                BPROCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPROCAC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPROCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPROCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
            }
            CEP.TRC(SCCGWA, BPROCAC.BV_TYP);
            CEP.TRC(SCCGWA, "CREATE BEGIN");
            CEP.TRC(SCCGWA, BPCSOCAC.AC);
            CEP.TRC(SCCGWA, BPCSOCAC.ACO_AC);
            CEP.TRC(SCCGWA, BPCSOCAC.WORK_TYP);
            CEP.TRC(SCCGWA, BPCSOCAC.IC_AID_FLG);
            if (BPCSOCAC.WORK_TYP.equalsIgnoreCase("22") 
                && BPCSOCAC.IC_AID_FLG == 'N') {
            } else {
                B010_CREATE_RECORD_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTOCAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTOCAC();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "PINGZHENHUHUAN");
        CEP.TRC(SCCGWA, BPCSOCAC.NEW_BV_NO);
        BPROCAC.KEY.AC = BPCSOCAC.OLD_AC;
        BPROCAC.KEY.ACO_AC = BPCSOCAC.ACO_AC;
        IBS.init(SCCGWA, BPCROCAC);
        BPCROCAC.INFO.FUNC = 'R';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        R000_READ_DATA();
        if (pgmRtn) return;
        BPROCAC.STS = 'C';
        BPROCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPROCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPROCAC.CLO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPROCAC.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPROCAC.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCROCAC);
        BPCROCAC.INFO.FUNC = 'U';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCROCAC);
        BPROCAC.KEY.AC = BPCSOCAC.AC;
        BPROCAC.KEY.ACO_AC = BPCSOCAC.ACO_AC;
        BPCROCAC.INFO.FUNC = 'R';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        if (BPCROCAC.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, WS_DATA.WS_STS);
            R000_MOVES_DATA();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPROCAC);
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSOCAC.STS);
            CEP.TRC(SCCGWA, "NEW BV-NO");
            CEP.TRC(SCCGWA, BPCSOCAC.NEW_BV_NO);
            BPROCAC.BV_TYP = BPCSOCAC.NEW_BV_TYP;
            BPROCAC.BV_NO = BPCSOCAC.NEW_BV_NO;
            BPROCAC.SEQ = BPCSOCAC.NEW_SEQ;
            CEP.TRC(SCCGWA, BPROCAC.BV_NO);
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            BPROCAC.KEY.AC = BPCSOCAC.AC;
            BPROCAC.KEY.ACO_AC = BPCSOCAC.ACO_AC;
            BPROCAC.STS = 'N';
            BPROCAC.BV_TYP = BPCSOCAC.NEW_BV_TYP;
            BPROCAC.BV_NO = BPCSOCAC.NEW_BV_NO;
            BPROCAC.SEQ = BPCSOCAC.NEW_SEQ;
            BPROCAC.CLOSE_DATE = 0;
            BPROCAC.CLOSE_TLR = " ";
            BPROCAC.REOPN_DATE = 0;
            BPROCAC.CLOSE_REASON = " ";
            BPROCAC.CLOSE_NO = " ";
            BPROCAC.CLOSE_AC_STS = ' ';
            BPROCAC.LOSS_RAT = 0;
            BPROCAC.LOSS_INT = 0;
            BPROCAC.LOSS_TAX = 0;
            BPROCAC.LOSS_AMT = 0;
            BPROCAC.CLO_BR = 0;
            BPROCAC.LOSS_TYP = ' ';
            IBS.init(SCCGWA, BPCROCAC);
            BPCROCAC.INFO.FUNC = 'U';
            S000_CALL_BPZROCAC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPROCAC.STS);
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPROCAC.KEY.AC = BPCSOCAC.AC;
        BPROCAC.KEY.ACO_AC = BPCSOCAC.ACO_AC;
        IBS.init(SCCGWA, BPCROCAC);
        BPCROCAC.INFO.FUNC = 'R';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        R000_READ_DATA();
        if (pgmRtn) return;
        if (BPCSOCAC.STS == 'N') {
            BPROCAC.OPEN_NO = BPCSOCAC.OPEN_NO;
        } else {
            if (BPCSOCAC.STS == ' ') {
                BPROCAC.STS = 'C';
            } else {
                BPROCAC.STS = BPCSOCAC.STS;
            }
            CEP.TRC(SCCGWA, "B040 UDATE");
            CEP.TRC(SCCGWA, BPCSOCAC.LOSS_INT);
            BPROCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPROCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPROCAC.REOPN_DATE = BPCSOCAC.REOPN_DATE;
            BPROCAC.CLOSE_REASON = BPCSOCAC.CLOSE_REASON;
            BPROCAC.CLOSE_NO = BPCSOCAC.CLOSE_NO;
            BPROCAC.CLOSE_AC_STS = BPCSOCAC.CLOSE_AC_STS;
            BPROCAC.LOSS_RAT = BPCSOCAC.LOSS_RAT;
            BPROCAC.LOSS_INT = BPCSOCAC.LOSS_INT;
            BPROCAC.LOSS_TAX = BPCSOCAC.LOSS_TAX;
            BPROCAC.LOSS_AMT = BPCSOCAC.LOSS_AMT;
            if (BPCSOCAC.LOSS_TYP != ' ') {
                BPROCAC.LOSS_TYP = BPCSOCAC.LOSS_TYP;
            }
            if (BPCSOCAC.CLO_BR != 0) {
                BPROCAC.CLO_BR = BPCSOCAC.CLO_BR;
            } else {
                BPROCAC.CLO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            }
        }
        BPROCAC.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPROCAC.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCROCAC);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_INT);
        CEP.TRC(SCCGWA, BPROCAC.STS);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_AC_STS);
        BPCROCAC.INFO.FUNC = 'U';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        R000_MOVES_DATA();
        if (pgmRtn) return;
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCROCAC);
        BPROCAC.KEY.AC = BPCSOCAC.AC;
        BPROCAC.KEY.ACO_AC = BPCSOCAC.ACO_AC;
        BPCROCAC.INFO.FUNC = 'R';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        if (BPCSOCAC.STS == 'N') {
            BPROCAC.STS = 'R';
        } else {
            BPROCAC.STS = 'N';
            BPROCAC.CLOSE_DATE = 0;
            BPROCAC.CLOSE_TLR = " ";
            BPROCAC.REOPN_DATE = 0;
            BPROCAC.CLOSE_REASON = " ";
            BPROCAC.CLOSE_NO = " ";
            BPROCAC.CLOSE_AC_STS = ' ';
            BPROCAC.LOSS_RAT = 0;
            BPROCAC.LOSS_INT = 0;
            BPROCAC.LOSS_TAX = 0;
            BPROCAC.LOSS_AMT = 0;
            BPROCAC.CLO_BR = 0;
            BPROCAC.LOSS_TYP = ' ';
        }
        BPROCAC.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPROCAC.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCROCAC);
        BPCROCAC.INFO.FUNC = 'U';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
    }
    public void B060_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSOCAC.AC);
        CEP.TRC(SCCGWA, BPCSOCAC.ACO_AC);
        BPROCAC.KEY.AC = BPCSOCAC.AC;
        BPROCAC.KEY.ACO_AC = BPCSOCAC.ACO_AC;
        IBS.init(SCCGWA, BPCROCAC);
        BPCROCAC.INFO.FUNC = 'R';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSOCAC.BV_NO);
        BPROCAC.STS = 'N';
        BPROCAC.BV_NO = BPCSOCAC.BV_NO;
        BPROCAC.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPROCAC.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCROCAC);
        CEP.TRC(SCCGWA, BPROCAC.STS);
        BPCROCAC.INFO.FUNC = 'U';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
    }
    public void B070_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSOCAC.AC);
        CEP.TRC(SCCGWA, BPCSOCAC.ACO_AC);
        BPROCAC.KEY.AC = BPCSOCAC.AC;
        BPROCAC.KEY.ACO_AC = BPCSOCAC.ACO_AC;
        IBS.init(SCCGWA, BPCROCAC);
        BPCROCAC.INFO.FUNC = 'R';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSOCAC.BR);
        BPROCAC.STS = 'N';
        BPROCAC.BR = BPCSOCAC.BR;
        BPROCAC.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPROCAC.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCROCAC);
        CEP.TRC(SCCGWA, BPROCAC.STS);
        BPCROCAC.INFO.FUNC = 'U';
        S000_CALL_BPZROCAC();
        if (pgmRtn) return;
    }
    public void R000_READ_DATA() throws IOException,SQLException,Exception {
        WS_DATA.WS_AC = BPROCAC.KEY.AC;
        WS_DATA.WS_ACO_AC = BPROCAC.KEY.ACO_AC;
        WS_DATA.WS_STS = BPROCAC.STS;
        WS_DATA.WS_WORK_TYP = BPROCAC.WORK_TYP;
        WS_DATA.WS_CAL_TYP = BPROCAC.CAL_TYP;
        WS_DATA.WS_LOSS_TYP = BPROCAC.LOSS_TYP;
        WS_DATA.WS_CI_TYPE = BPROCAC.CI_TYPE;
        WS_DATA.WS_BV_TYP = BPROCAC.BV_TYP;
        WS_DATA.WS_BV_NO = BPROCAC.BV_NO;
        WS_DATA.WS_ID_TYP = BPROCAC.ID_TYP;
        WS_DATA.WS_ID_NO = BPROCAC.ID_NO;
        WS_DATA.WS_CI_CNM = BPROCAC.CI_CNM;
        WS_DATA.WS_CARD_FLG = BPROCAC.CARD_FLG;
        WS_DATA.WS_SEQ = BPROCAC.SEQ;
        WS_DATA.WS_CCY = BPROCAC.CCY;
        WS_DATA.WS_CCY_TYPE = BPROCAC.CCY_TYPE;
        WS_DATA.WS_AUT_TLR = BPROCAC.AUT_TLR;
        WS_DATA.WS_OPEN_DATE = BPROCAC.OPEN_DATE;
        WS_DATA.WS_CLOSE_DATE = BPROCAC.CLOSE_DATE;
        WS_DATA.WS_OPEN_TLR = BPROCAC.OPEN_TLR;
        WS_DATA.WS_CLOSE_TLR = BPROCAC.CLOSE_TLR;
        WS_DATA.WS_OPEN_NO = BPROCAC.OPEN_NO;
        WS_DATA.WS_REOPN_DATE = BPROCAC.REOPN_DATE;
        WS_DATA.WS_CLOSE_REASON = BPROCAC.CLOSE_REASON;
        WS_DATA.WS_CLOSE_NO = BPROCAC.CLOSE_NO;
        WS_DATA.WS_CLOSE_AC_STS = BPROCAC.CLOSE_AC_STS;
        WS_DATA.WS_LOSS_RAT = BPROCAC.LOSS_RAT;
        WS_DATA.WS_LOSS_INT = BPROCAC.LOSS_INT;
        WS_DATA.WS_LOSS_TAX = BPROCAC.LOSS_TAX;
        WS_DATA.WS_LOSS_AMT = BPROCAC.LOSS_AMT;
        WS_DATA.WS_CHNL_NO = BPROCAC.CHNL_NO;
        WS_DATA.WS_OPEN_AMT = BPROCAC.OPEN_AMT;
        WS_DATA.WS_PROD_CD = BPROCAC.PROD_CD;
        WS_DATA.WS_REMARK = BPROCAC.REMARK;
        WS_DATA.WS_OTH_RPT_NM = BPROCAC.OTH_PRT_NM;
        WS_DATA.WS_OTH_ID_TYP = BPROCAC.OTH_ID_TYP;
        WS_DATA.WS_OTH_ID_NO = BPROCAC.OTH_ID_NO;
        WS_DATA.WS_BR = BPROCAC.BR;
        WS_DATA.WS_CLO_BR = BPROCAC.CLO_BR;
        WS_DATA.WS_CREATE_DT = BPROCAC.CREATE_DT;
        WS_DATA.WS_CREATE_TLR = BPROCAC.CREATE_TLR;
        WS_DATA.WS_OPEN_RAT = BPROCAC.OPEN_RAT;
        WS_DATA.WS_AC_CNM = BPROCAC.AC_CNM;
        WS_DATA.WS_COL_IND = BPROCAC.COL_IND;
    }
    public void R000_MOVES_DATA() throws IOException,SQLException,Exception {
        BPCSOCAC.ACO_AC = WS_DATA.WS_ACO_AC;
        BPCSOCAC.STS = WS_DATA.WS_STS;
        BPCSOCAC.WORK_TYP = WS_DATA.WS_WORK_TYP;
        BPCSOCAC.CAL_TYP = WS_DATA.WS_CAL_TYP;
        BPCSOCAC.LOSS_TYP = WS_DATA.WS_LOSS_TYP;
        BPCSOCAC.CI_TYPE = WS_DATA.WS_CI_TYPE;
        BPCSOCAC.BV_TYP = WS_DATA.WS_BV_TYP;
        BPCSOCAC.BV_NO = WS_DATA.WS_BV_NO;
        BPCSOCAC.ID_TYP = WS_DATA.WS_ID_TYP;
        BPCSOCAC.ID_NO = WS_DATA.WS_ID_NO;
        BPCSOCAC.CI_CNM = WS_DATA.WS_CI_CNM;
        BPCSOCAC.CARD_FLG = WS_DATA.WS_CARD_FLG;
        BPCSOCAC.SEQ = WS_DATA.WS_SEQ;
        BPCSOCAC.CCY = WS_DATA.WS_CCY;
        BPCSOCAC.CCY_TYPE = WS_DATA.WS_CCY_TYPE;
        BPCSOCAC.AUT_TLR = WS_DATA.WS_AUT_TLR;
        BPCSOCAC.OPEN_DATE = WS_DATA.WS_OPEN_DATE;
        BPCSOCAC.CLOSE_DATE = WS_DATA.WS_CLOSE_DATE;
        BPCSOCAC.OPEN_TLR = WS_DATA.WS_OPEN_TLR;
        BPCSOCAC.CLOSE_TLR = WS_DATA.WS_CLOSE_TLR;
        BPCSOCAC.OPEN_NO = WS_DATA.WS_OPEN_NO;
        BPCSOCAC.REOPN_DATE = WS_DATA.WS_REOPN_DATE;
        BPCSOCAC.CLOSE_REASON = WS_DATA.WS_CLOSE_REASON;
        BPCSOCAC.CLOSE_NO = WS_DATA.WS_CLOSE_NO;
        BPCSOCAC.CLOSE_AC_STS = WS_DATA.WS_CLOSE_AC_STS;
        BPCSOCAC.LOSS_RAT = WS_DATA.WS_LOSS_RAT;
        BPCSOCAC.LOSS_INT = WS_DATA.WS_LOSS_INT;
        BPCSOCAC.LOSS_TAX = WS_DATA.WS_LOSS_TAX;
        BPCSOCAC.LOSS_AMT = WS_DATA.WS_LOSS_AMT;
        BPCSOCAC.CHNL_NO = WS_DATA.WS_CHNL_NO;
        BPCSOCAC.OPEN_AMT = WS_DATA.WS_OPEN_AMT;
        BPCSOCAC.PROD_CD = WS_DATA.WS_PROD_CD;
        BPCSOCAC.REMARK = WS_DATA.WS_REMARK;
        BPCSOCAC.OTH_RPT_NM = WS_DATA.WS_OTH_RPT_NM;
        BPCSOCAC.OTH_ID_TYP = WS_DATA.WS_OTH_ID_TYP;
        BPCSOCAC.OTH_ID_NO = WS_DATA.WS_OTH_ID_NO;
        BPCSOCAC.BR = WS_DATA.WS_BR;
        BPCSOCAC.CLO_BR = WS_DATA.WS_CLO_BR;
        BPCSOCAC.CREATE_DT = WS_DATA.WS_CREATE_DT;
        BPCSOCAC.CREATE_TLR = WS_DATA.WS_CREATE_TLR;
        BPCSOCAC.OPEN_RAT = WS_DATA.WS_OPEN_RAT;
        BPCSOCAC.AC_CNM = WS_DATA.WS_AC_CNM;
        BPCSOCAC.COL_IND = WS_DATA.WS_COL_IND;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.AGR_NO = BPCSOCAC.AC;
        CICCUST.FUNC = 'A';
        S00_CALL_CIZCUST();
        if (pgmRtn) return;
        BPROCAC.COL_IND = CICCUST.O_DATA.O_COL_IND_FLG;
        CEP.TRC(SCCGWA, BPROCAC.COL_IND);
        BPROCAC.KEY.AC = BPCSOCAC.AC;
        BPROCAC.KEY.ACO_AC = BPCSOCAC.ACO_AC;
        BPROCAC.STS = BPCSOCAC.STS;
        BPROCAC.WORK_TYP = BPCSOCAC.WORK_TYP;
        BPROCAC.CAL_TYP = BPCSOCAC.CAL_TYP;
        BPROCAC.LOSS_TYP = BPCSOCAC.LOSS_TYP;
        BPROCAC.CI_TYPE = BPCSOCAC.CI_TYPE;
        BPROCAC.BV_TYP = BPCSOCAC.BV_TYP;
        BPROCAC.BV_NO = BPCSOCAC.BV_NO;
        BPROCAC.ID_TYP = BPCSOCAC.ID_TYP;
        BPROCAC.ID_NO = BPCSOCAC.ID_NO;
        BPROCAC.CI_CNM = BPCSOCAC.CI_CNM;
        BPROCAC.CARD_FLG = BPCSOCAC.CARD_FLG;
        BPROCAC.SEQ = BPCSOCAC.SEQ;
        BPROCAC.CCY = BPCSOCAC.CCY;
        BPROCAC.CCY_TYPE = BPCSOCAC.CCY_TYPE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        BPROCAC.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        CEP.TRC(SCCGWA, BPCSOCAC.CLOSE_DATE);
        BPROCAC.OPEN_DATE = BPCSOCAC.OPEN_DATE;
        BPROCAC.CLOSE_DATE = BPCSOCAC.CLOSE_DATE;
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_DATE);
        if (BPCSOCAC.STS == 'N') {
            if (BPCSOCAC.OPEN_TLR.trim().length() == 0) {
                BPROCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                BPROCAC.OPEN_TLR = BPCSOCAC.OPEN_TLR;
            }
            CEP.TRC(SCCGWA, BPCSOCAC.BR);
            if (BPCSOCAC.BR == 0) {
                BPROCAC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            } else {
                BPROCAC.BR = BPCSOCAC.BR;
            }
            CEP.TRC(SCCGWA, BPROCAC.BR);
            CEP.TRC(SCCGWA, BPCSOCAC.CHNL_NO);
            if (BPCSOCAC.CHNL_NO.trim().length() == 0) {
                BPROCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
            } else {
                BPROCAC.CHNL_NO = BPCSOCAC.CHNL_NO;
            }
            CEP.TRC(SCCGWA, BPROCAC.CHNL_NO);
        }
        if (BPCSOCAC.STS == 'C') {
            if (BPCSOCAC.CLOSE_TLR.trim().length() == 0) {
                BPROCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                BPROCAC.CLOSE_TLR = BPCSOCAC.CLOSE_TLR;
            }
            if (BPCSOCAC.CLO_BR == 0) {
                BPROCAC.CLO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            } else {
                BPROCAC.CLO_BR = BPCSOCAC.CLO_BR;
            }
        }
        BPROCAC.OPEN_NO = BPCSOCAC.OPEN_NO;
        BPROCAC.REOPN_DATE = BPCSOCAC.REOPN_DATE;
        BPROCAC.CLOSE_REASON = BPCSOCAC.CLOSE_REASON;
        BPROCAC.CLOSE_NO = BPCSOCAC.CLOSE_NO;
        BPROCAC.CLOSE_AC_STS = BPCSOCAC.CLOSE_AC_STS;
        BPROCAC.LOSS_RAT = BPCSOCAC.LOSS_RAT;
        BPROCAC.LOSS_INT = BPCSOCAC.LOSS_INT;
        BPROCAC.LOSS_TAX = BPCSOCAC.LOSS_TAX;
        BPROCAC.LOSS_AMT = BPCSOCAC.LOSS_AMT;
        BPROCAC.OPEN_AMT = BPCSOCAC.OPEN_AMT;
        BPROCAC.PROD_CD = BPCSOCAC.PROD_CD;
        BPROCAC.REMARK = BPCSOCAC.REMARK;
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
            CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.CI_NM);
            CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.ID_TYP);
            CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.ID_NO);
            if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
                BPROCAC.OTH_PRT_NM = CICGAGA_AGENT_AREA.CI_NM;
                BPROCAC.OTH_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
                BPROCAC.OTH_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            }
        }
        if (BPCSOCAC.FUNC == 'C') {
            if (BPCSOCAC.CREATE_DT == 0 
                || BPCSOCAC.CREATE_DT == ' ') {
                BPROCAC.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPROCAC.CREATE_DT = BPCSOCAC.CREATE_DT;
            }
            if (BPCSOCAC.CREATE_TLR.trim().length() == 0) {
                BPROCAC.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                BPROCAC.CREATE_TLR = BPCSOCAC.CREATE_TLR;
            }
        }
        BPROCAC.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPROCAC.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPROCAC.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPROCAC.OPEN_RAT = BPCSOCAC.OPEN_RAT;
        BPROCAC.AC_CNM = BPCSOCAC.AC_CNM;
        CEP.TRC(SCCGWA, BPROCAC.BR);
    }
    public void S00_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CI_CIZCUST, CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSOCAC.RC);
            CEP.ERR(SCCGWA, BPCROCAC.RC);
        }
    }
    public void T000_STARTBR_BPTOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        BPROCAC.KEY.AC = BPCSOCAC.OLD_AC;
        BPTOCAC_BR.rp = new DBParm();
        BPTOCAC_BR.rp.TableName = "BPTOCAC";
        BPTOCAC_BR.rp.where = "AC = :BPROCAC.KEY.AC";
        IBS.STARTBR(SCCGWA, BPROCAC, this, BPTOCAC_BR);
    }
    public void T000_READNEXT_BPTOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPROCAC, this, BPTOCAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_OCAC_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_OCAC_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTOCAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTOCAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTOCAC_BR);
    }
    public void S000_CALL_BPZROCAC() throws IOException,SQLException,Exception {
        BPCROCAC.INFO.POINTER = BPROCAC;
        BPCROCAC.INFO.REC_LEN = 1505;
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-OCAC-INFO", BPCROCAC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCROCAC.RC);
        if (BPCROCAC.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_OCAC_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCROCAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSOCAC.RC);
            CEP.ERR(SCCGWA, BPCROCAC.RC);
        }
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
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCSOCAC = ");
            CEP.TRC(SCCGWA, BPCSOCAC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
