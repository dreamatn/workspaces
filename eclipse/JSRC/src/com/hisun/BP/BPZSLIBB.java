package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLIBB {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTAPAR_RD;
    DBParm BPTALIB_RD;
    boolean pgmRtn = false;
    String CPN_BR_CASHAPP_BRW = "BP-BR-CASHAPP-BRW";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ";
    String CPN_BR_CASHAPP_READ = "BP-BR-CASHAPP-READ";
    char K_RUN_MODE = 'O';
    short K_NUM = 1;
    String K_BPFBAS_SEQ = "CASHMOVE";
    String K_SEQ_TYPE = "CMOVE";
    String K_HIS_REMARKS = "CS CHU RU KU SHEN QING ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOLIBR BPCOLIBR = new BPCOLIBR();
    BPRALIB BPRALIB = new BPRALIB();
    BPRAPAR BPRAPAR = new BPRAPAR();
    BPCOALBO BPCOALBO = new BPCOALBO();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCSLIBA BPCSLIBA = new BPCSLIBA();
    BPCSLIBR BPCSLIBR = new BPCSLIBR();
    BPCOLIBQ BPCOLIBQ = new BPCOLIBQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCOLIBB BPCOLIBB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOLIBB BPCOLIBB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOLIBB = BPCOLIBB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        B030_HISTORY_RECORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSLIBB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOLIBB.FUNC == 'B') {
            B010_BROWSE_DATA_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOLIBB.FUNC == 'M') {
            B020_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOLIBB.FUNC == 'I') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOLIBB.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR1);
        }
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P901";
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
    public void B010_BROWSE_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLIBA);
        BPCSLIBA.APP_NO = BPCOLIBB.APP_NO;
        BPCSLIBA.APP_BR = BPCOLIBB.APP_BR;
        BPCSLIBA.UP_BR = BPCOLIBB.UP_BR;
        BPCSLIBA.APP_TYPE = BPCOLIBB.APP_TYPE;
        BPCSLIBA.APP_STS = BPCOLIBB.APP_STS;
        BPCSLIBA.BEG_DT = BPCOLIBB.BEG_DT;
        BPCSLIBA.END_DT = BPCOLIBB.END_DT;
        BPCSLIBA.ALLOT_TP = BPCOLIBB.ALLOT_TP;
        S000_CALL_BPZSLIBA();
        if (pgmRtn) return;
    }
    public void B020_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCOLIBB.MODIFY_STS != 'U' 
            && BPCOLIBB.MODIFY_STS != 'R' 
            && BPCOLIBB.MODIFY_STS != 'A' 
            && BPCOLIBB.MODIFY_STS != 'C' 
            && BPCOLIBB.MODIFY_STS != 'D' 
            && BPCOLIBB.MODIFY_STS != 'Q' 
            && BPCOLIBB.MODIFY_STS != 'K' 
            && BPCOLIBB.MODIFY_STS != 'I') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR2);
        }
        IBS.init(SCCGWA, BPRALIB);
        if (BPCOLIBB.FLG == '2' 
            && BPCOLIBB.MODIFY_STS == 'Q') {
        } else {
            CEP.TRC(SCCGWA, BPCOLIBB.APP_NO);
            BPRALIB.KEY.APP_NO = BPCOLIBB.APP_NO;
            TOOO_READ_BPTALIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRALIB.APP_TYPE);
            CEP.TRC(SCCGWA, BPCOLIBB.MODIFY_STS);
            CEP.TRC(SCCGWA, BPRALIB.APP_STS);
        }
        if (BPCOLIBB.MODIFY_STS == 'U') {
            if (BPRALIB.APP_STS == '0') {
                BPRALIB.APP_STS = ' ';
                BPRALIB.BACK_TLR = " ";
                BPRALIB.BACK_DT = 0;
                BPRALIB.BACK_TM = 0;
                BPRALIB.UPD_TLR = " ";
                BPRALIB.UPD_DT = 0;
                BPRALIB.APP_STS = '2';
                BPRALIB.BACK_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPRALIB.BACK_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRALIB.BACK_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR3);
            }
        }
        if (BPRALIB.APP_TYPE == '0') {
            if (BPCOLIBB.MODIFY_STS == 'R') {
                if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR4);
                }
                if (BPRALIB.APP_STS == '0' 
                    || BPRALIB.APP_STS == '1' 
                    || BPRALIB.APP_STS == '3' 
                    || BPRALIB.APP_STS == '4') {
                    BPRALIB.APP_STS = ' ';
                    BPRALIB.REJ_TLR = " ";
                    BPRALIB.REJ_DT = 0;
                    BPRALIB.REJ_TM = 0;
                    BPRALIB.UPD_TLR = " ";
                    BPRALIB.UPD_DT = 0;
                    BPRALIB.APP_STS = '7';
                    BPRALIB.REJ_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.REJ_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRALIB.REJ_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR5);
                }
            }
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                if (BPCOLIBB.MODIFY_STS == 'R') {
                    if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR6);
                    }
                    if (BPRALIB.APP_STS == '0' 
                        || BPRALIB.APP_STS == '1' 
                        || BPRALIB.APP_STS == '4' 
                        || BPRALIB.APP_STS == '5') {
                        BPRALIB.APP_STS = ' ';
                        BPRALIB.REJ_TLR = " ";
                        BPRALIB.REJ_DT = 0;
                        BPRALIB.REJ_TM = 0;
                        BPRALIB.UPD_TLR = " ";
                        BPRALIB.UPD_DT = 0;
                        BPRALIB.APP_STS = '7';
                        BPRALIB.REJ_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.REJ_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRALIB.REJ_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR7);
                    }
                }
            }
        }
        if (BPRALIB.APP_TYPE == '0') {
            if (BPCOLIBB.MODIFY_STS == 'A') {
                CEP.TRC(SCCGWA, BPRALIB.UP_BR);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
                if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR8);
                }
                if (BPRALIB.APP_STS == '0') {
                    BPRALIB.APP_STS = ' ';
                    BPRALIB.ACP_TLR = " ";
                    BPRALIB.ACP_DT = 0;
                    BPRALIB.ACP_TM = 0;
                    BPRALIB.UPD_TLR = " ";
                    BPRALIB.UPD_DT = 0;
                    BPRALIB.APP_STS = '1';
                    BPRALIB.ACP_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.ACP_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRALIB.ACP_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR10);
                }
            }
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                if (BPCOLIBB.MODIFY_STS == 'A') {
                    if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR11);
                    }
                    if (BPRALIB.APP_STS == '5') {
                        BPRALIB.APP_STS = ' ';
                        BPRALIB.ACP_TLR = " ";
                        BPRALIB.ACP_DT = 0;
                        BPRALIB.ACP_TM = 0;
                        BPRALIB.UPD_TLR = " ";
                        BPRALIB.UPD_DT = 0;
                        BPRALIB.APP_STS = '1';
                        BPRALIB.ACP_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.ACP_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRALIB.ACP_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR13);
                    }
                }
            }
        }
        if (BPRALIB.APP_TYPE == '0') {
            if (BPCOLIBB.MODIFY_STS == 'C') {
                CEP.TRC(SCCGWA, BPRALIB.APP_STS);
                if (BPRALIB.APP_STS == '4') {
                    CEP.TRC(SCCGWA, BPRALIB.UP_BR);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                    CEP.TRC(SCCGWA, BPRALIB.ADT_TLR);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
                    if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.TRC(SCCGWA, "DEV1");
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR14);
                    }
                    if (!BPRALIB.ADT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        CEP.TRC(SCCGWA, "DEV2");
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR15);
                    }
                    BPRALIB.APP_STS = ' ';
                    BPRALIB.UNDO_TLR = " ";
                    BPRALIB.UNDO_DT = 0;
                    BPRALIB.UNDO_TM = 0;
                    BPRALIB.UPD_TLR = " ";
                    BPRALIB.UPD_DT = 0;
                    BPRALIB.APP_STS = '3';
                    BPRALIB.UNDO_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.UNDO_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRALIB.UNDO_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR16);
                }
            }
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                if (BPCOLIBB.MODIFY_STS == 'C') {
                    if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.TRC(SCCGWA, "DEV1");
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR170);
                    }
                    if (BPRALIB.APP_STS != '4' 
                        && BPRALIB.APP_STS != '1') {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR69);
                    } else {
                        if (BPRALIB.APP_STS == '1') {
                            BPRALIB.APP_STS = '5';
                            BPRALIB.UNDO_TLR = SCCGWA.COMM_AREA.TL_ID;
                            BPRALIB.UNDO_DT = SCCGWA.COMM_AREA.AC_DATE;
                            BPRALIB.UNDO_TM = SCCGWA.COMM_AREA.TR_TIME;
                            BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                            BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        } else {
                            if (BPRALIB.APP_STS == '4') {
                                BPRALIB.APP_STS = '1';
                                BPRALIB.UNDO_TLR = SCCGWA.COMM_AREA.TL_ID;
                                BPRALIB.UNDO_DT = SCCGWA.COMM_AREA.AC_DATE;
                                BPRALIB.UNDO_TM = SCCGWA.COMM_AREA.TR_TIME;
                                BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                                BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                            }
                        }
                    }
                }
            }
        }
        if (BPRALIB.APP_TYPE == '0') {
            if (BPCOLIBB.MODIFY_STS == 'D') {
                if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR17);
                }
                if (BPRALIB.CONF_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                    || BPRALIB.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR18);
                }
                if (BPRALIB.APP_STS == '3') {
                    BPRALIB.APP_STS = ' ';
                    BPRALIB.ADT_TLR = " ";
                    BPRALIB.ADT_DT = 0;
                    BPRALIB.ADT_TM = 0;
                    BPRALIB.UPD_TLR = " ";
                    BPRALIB.UPD_DT = 0;
                    BPRALIB.APP_STS = '4';
                    BPRALIB.ADT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.ADT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRALIB.ADT_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR19);
                }
            }
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                if (BPCOLIBB.MODIFY_STS == 'D') {
                    if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR20);
                    }
                    if (BPRALIB.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR21);
                    }
                    if (BPRALIB.APP_STS == '1') {
                        BPRALIB.APP_STS = ' ';
                        BPRALIB.ADT_TLR = " ";
                        BPRALIB.ADT_DT = 0;
                        BPRALIB.ADT_TM = 0;
                        BPRALIB.UPD_TLR = " ";
                        BPRALIB.UPD_DT = 0;
                        BPRALIB.APP_STS = '4';
                        BPRALIB.ADT_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.ADT_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRALIB.ADT_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR22);
                    }
                }
            }
        }
        if (BPCOLIBB.MODIFY_STS == 'Q') {
            CEP.TRC(SCCGWA, BPCOLIBB.FLG);
            CEP.TRC(SCCGWA, BPRALIB.APP_TYPE);
            if (BPCOLIBB.FLG == '2') {
                BPRALIB.KEY.APP_NO = BPCOLIBB.APP_NO;
                BPRALIB.APP_BR = BPCOLIBB.APP_BR;
                BPRALIB.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRALIB.APP_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRALIB.APP_TYPE = BPCOLIBB.APP_TYPE;
                BPRALIB.UP_BR = BPCOLIBB.UP_BR;
                BPRALIB.ALLOT_TYPE = BPCOLIBB.ALLOT_TP;
                BPRALIB.APP_STS = '3';
                BPRALIB.APP_TYPE = BPCOLIBB.APP_TYPE;
                BPRALIB.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRALIB.CREATE_TLR = BPCOLIBB.UP_TLR;
                BPRALIB.OWNER_BK = BPCOLIBB.UP_BR;
            }
            CEP.TRC(SCCGWA, BPRALIB.APP_TYPE);
            if (BPRALIB.APP_TYPE == '0') {
                if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                    && BPCOLIBB.FLG == '2') {
                } else {
                    CEP.TRC(SCCGWA, BPRALIB.UP_BR);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                    IBS.init(SCCGWA, BPCFTLRQ);
                    BPCFTLRQ.INFO.TLR = BPCOLIBB.UP_TLR;
                    S000_CALL_BPZFTLRQ();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
                    if (BPCFTLRQ.INFO.NEW_BR != BPRALIB.UP_BR) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR138);
                    }
                    CEP.TRC(SCCGWA, BPRALIB.ACP_TLR);
                    CEP.TRC(SCCGWA, BPCOLIBB.UP_TLR);
                    if (!BPRALIB.ACP_TLR.equalsIgnoreCase(BPCOLIBB.UP_TLR)) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR24);
                    }
                }
                if (BPRALIB.APP_STS == '1' 
                    || BPRALIB.APP_STS == '3') {
                    BPRALIB.APP_STS = ' ';
                    BPRALIB.CONF_TLR = " ";
                    BPRALIB.CONF_DT = 0;
                    BPRALIB.CONF_TM = 0;
                    BPRALIB.UPD_TLR = " ";
                    BPRALIB.UPD_DT = 0;
                    BPRALIB.APP_STS = '3';
                    BPRALIB.CONF_TLR = BPCOLIBB.UP_TLR;
                    BPRALIB.CONF_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRALIB.CONF_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRALIB.UPD_TLR = BPCOLIBB.UP_TLR;
                    BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR25);
                }
            } else {
                if (BPRALIB.APP_TYPE == '1') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR92);
                }
            }
        }
        if (BPRALIB.APP_TYPE == '0') {
            if (BPCOLIBB.MODIFY_STS == 'K') {
                if (BPRALIB.APP_STS == '4') {
                    if (BPRALIB.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                        || BPRALIB.CONF_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                        || BPRALIB.ADT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                        || BPRALIB.UNDO_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR26);
                    }
                    BPRALIB.CONF_NO = 0;
                    BPRALIB.APP_STS = ' ';
                    BPRALIB.OUT_TLR = " ";
                    BPRALIB.OUT_DT = 0;
                    BPRALIB.OUT_TM = 0;
                    BPRALIB.UPD_TLR = " ";
                    BPRALIB.UPD_DT = 0;
                    BPRALIB.MOV_DT = 0;
                    BPRALIB.CONF_NO = BPCOLIBB.CONF_SEQ;
                    BPRALIB.APP_STS = '5';
                    BPRALIB.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.OUT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRALIB.OUT_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRALIB.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
                    T000_REWRITE_BPTALIB();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCOLIBB);
                    BPCOLIBB.MOV_DT = BPRALIB.MOV_DT;
                    BPCOLIBB.APP_TYPE = BPRALIB.APP_TYPE;
                    BPCOLIBB.IN_BR = BPRALIB.APP_BR;
                    BPCOLIBB.IN_TLR = BPRALIB.APP_TLR;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR27);
                }
            }
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                if (BPCOLIBB.MODIFY_STS == 'K') {
                    if (BPRALIB.APP_STS == '0') {
                        if (!BPRALIB.APP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR172);
                        }
                        BPRALIB.CONF_NO = 0;
                        BPRALIB.APP_STS = ' ';
                        BPRALIB.OUT_TLR = " ";
                        BPRALIB.OUT_DT = 0;
                        BPRALIB.OUT_TM = 0;
                        BPRALIB.UPD_TLR = " ";
                        BPRALIB.UPD_DT = 0;
                        BPRALIB.MOV_DT = 0;
                        BPRALIB.CONF_NO = BPCOLIBB.CONF_SEQ;
                        BPRALIB.APP_STS = '5';
                        BPRALIB.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.OUT_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRALIB.OUT_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRALIB.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
                        T000_REWRITE_BPTALIB();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, BPCOLIBB);
                        BPCOLIBB.MOV_DT = BPRALIB.MOV_DT;
                        BPCOLIBB.APP_TYPE = BPRALIB.APP_TYPE;
                        BPCOLIBB.IN_BR = BPRALIB.UP_BR;
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR28);
                    }
                }
            }
        }
        if (BPRALIB.APP_TYPE == '0') {
            if (BPCOLIBB.MODIFY_STS == 'I') {
                if (BPRALIB.APP_STS == '5') {
                    BPRALIB.APP_STS = ' ';
                    BPRALIB.IN_TLR = " ";
                    BPRALIB.IN_DT = 0;
                    BPRALIB.IN_TM = 0;
                    BPRALIB.UPD_TLR = " ";
                    BPRALIB.UPD_DT = 0;
                    BPRALIB.APP_STS = '6';
                    BPRALIB.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.IN_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRALIB.IN_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    T000_REWRITE_BPTALIB();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCOLIBB);
                    BPCOLIBB.MOV_DT = BPRALIB.MOV_DT;
                    BPCOLIBB.CONF_SEQ = BPRALIB.CONF_NO;
                    BPCOLIBB.APP_TYPE = BPRALIB.APP_TYPE;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR29);
                }
            }
        } else {
            if (BPRALIB.APP_TYPE == '1') {
                if (BPCOLIBB.MODIFY_STS == 'I') {
                    if (BPRALIB.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR31);
                    }
                    if (BPRALIB.APP_STS == '4') {
                        BPRALIB.APP_STS = ' ';
                        BPRALIB.IN_TLR = " ";
                        BPRALIB.IN_DT = 0;
                        BPRALIB.IN_TM = 0;
                        BPRALIB.UPD_TLR = " ";
                        BPRALIB.UPD_DT = 0;
                        BPRALIB.APP_STS = '6';
                        BPRALIB.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.IN_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRALIB.IN_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        T000_REWRITE_BPTALIB();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, BPCOLIBB);
                        BPCOLIBB.MOV_DT = BPRALIB.MOV_DT;
                        BPCOLIBB.CONF_SEQ = BPRALIB.CONF_NO;
                        BPCOLIBB.APP_TYPE = BPRALIB.APP_TYPE;
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR32);
                    }
                }
            }
        }
        if (BPCOLIBB.MODIFY_STS == 'Q' 
            && BPCOLIBB.FLG == '2') {
            T000_WRITE_BPTALIB();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_BPTALIB();
            if (pgmRtn) return;
        }
        if (BPCOLIBB.MODIFY_STS == 'Q') {
            R000_MODIFY_BPTAPAR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCOLIBB.CNT);
        if (BPCOLIBB.CNT > 2) {
        } else {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR141);
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPAR);
        BPRAPAR.KEY.APP_NO = BPCOLIBB.APP_NO;
        T000_READ_BPTAPAR();
        if (pgmRtn) return;
        R020_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        B010_ADD_APP_NO();
        if (pgmRtn) return;
        B010_CHECK_APPNO_BPTALIB_EXIST();
        if (pgmRtn) return;
        if (BPCSLIBR.RETURN_INFO == 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APPNO_EXIST);
        }
        B010_CHECK_APPNO_BPTAPAR_EXIST();
        if (pgmRtn) return;
        if (BPCSLIBR.RETURN_INFO == 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APPNO_EXIST);
        }
        B020_WRITE_BPTALIB();
        if (pgmRtn) return;
        B020_WRITE_BPTAPAR();
        if (pgmRtn) return;
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSLIBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BR_CASHAPP_READ, BPCSLIBR);
    }
    public void S000_CALL_BPZSLIBA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BR_CASHAPP_BRW, BPCSLIBA);
    }
    public void R000_MODIFY_BPTAPAR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPAR);
        if (BPCOLIBB.FLG == '2') {
            BPRAPAR.KEY.APP_NO = BPCOLIBB.APP_NO;
            BPRAPAR.APP_CCY = BPCOLIBB.CCY;
            BPRAPAR.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRAPAR.CREATE_TLR = BPCOLIBB.UP_TLR;
            BPRAPAR.OWNER_BK = BPCOLIBB.UP_BR;
        } else {
            BPRAPAR.KEY.APP_NO = BPCOLIBB.APP_NO;
            T000_READ_BPTAPAR();
            if (pgmRtn) return;
            if (BPCOLIBB.FLG == '1') {
                if (!BPRAPAR.APP_CCY.equalsIgnoreCase(BPCOLIBB.CCY)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR140);
                }
            }
        }
        if (BPCOLIBB.CNT > 2) {
            BPRAPAR.UPD_DT = 0;
            BPRAPAR.UPD_TLR = " ";
            CEP.TRC(SCCGWA, BPRAPAR.APP_AMT);
            CEP.TRC(SCCGWA, BPRAPAR.OUT_AMT);
            if (BPRAPAR.OUT_AMT == 0) {
                BPRAPAR.OUT_AMT = BPRAPAR.APP_AMT;
            }
            CEP.TRC(SCCGWA, BPRAPAR.OUT_AMT);
            BPRAPAR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRAPAR.UPD_TLR = BPCOLIBB.UP_TLR;
        } else {
            BPRAPAR.OUT_AMT = 0;
            BPRAPAR.UPD_DT = 0;
            BPRAPAR.UPD_TLR = " ";
            BPRAPAR.PVAL1 = 0;
            BPRAPAR.NUM1 = 0;
            BPRAPAR.PVAL2 = 0;
            BPRAPAR.NUM2 = 0;
            BPRAPAR.PVAL3 = 0;
            BPRAPAR.NUM3 = 0;
            BPRAPAR.PVAL4 = 0;
            BPRAPAR.NUM4 = 0;
            BPRAPAR.PVAL5 = 0;
            BPRAPAR.NUM5 = 0;
            BPRAPAR.PVAL6 = 0;
            BPRAPAR.NUM6 = 0;
            BPRAPAR.PVAL7 = 0;
            BPRAPAR.NUM7 = 0;
            BPRAPAR.PVAL8 = 0;
            BPRAPAR.NUM8 = 0;
            BPRAPAR.PVAL9 = 0;
            BPRAPAR.NUM9 = 0;
            BPRAPAR.PVAL10 = 0;
            BPRAPAR.NUM10 = 0;
            BPRAPAR.PVAL11 = 0;
            BPRAPAR.NUM11 = 0;
            BPRAPAR.PVAL12 = 0;
            BPRAPAR.NUM12 = 0;
            BPRAPAR.PVAL13 = 0;
            BPRAPAR.NUM13 = 0;
            BPRAPAR.PVAL14 = 0;
            BPRAPAR.NUM14 = 0;
            BPRAPAR.PVAL15 = 0;
            BPRAPAR.NUM15 = 0;
            BPRAPAR.PVAL16 = 0;
            BPRAPAR.NUM16 = 0;
            BPRAPAR.PVAL17 = 0;
            BPRAPAR.NUM17 = 0;
            BPRAPAR.PVAL18 = 0;
            BPRAPAR.NUM18 = 0;
            BPRAPAR.PVAL19 = 0;
            BPRAPAR.NUM19 = 0;
            BPRAPAR.PVAL20 = 0;
            BPRAPAR.NUM20 = 0;
            BPRAPAR.OUT_AMT = BPCOLIBB.OUT_AMT;
            BPRAPAR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRAPAR.UPD_TLR = BPCOLIBB.UP_TLR;
            BPRAPAR.PVAL1 = BPCOLIBB.PVAL_INFO[1-1].PVAL;
            BPRAPAR.NUM1 = BPCOLIBB.PVAL_INFO[1-1].NUM;
            BPRAPAR.PVAL2 = BPCOLIBB.PVAL_INFO[2-1].PVAL;
            BPRAPAR.NUM2 = BPCOLIBB.PVAL_INFO[2-1].NUM;
            BPRAPAR.PVAL3 = BPCOLIBB.PVAL_INFO[3-1].PVAL;
            BPRAPAR.NUM3 = BPCOLIBB.PVAL_INFO[3-1].NUM;
            BPRAPAR.PVAL4 = BPCOLIBB.PVAL_INFO[4-1].PVAL;
            BPRAPAR.NUM4 = BPCOLIBB.PVAL_INFO[4-1].NUM;
            BPRAPAR.PVAL5 = BPCOLIBB.PVAL_INFO[5-1].PVAL;
            BPRAPAR.NUM5 = BPCOLIBB.PVAL_INFO[5-1].NUM;
            BPRAPAR.PVAL6 = BPCOLIBB.PVAL_INFO[6-1].PVAL;
            BPRAPAR.NUM6 = BPCOLIBB.PVAL_INFO[6-1].NUM;
            BPRAPAR.PVAL7 = BPCOLIBB.PVAL_INFO[7-1].PVAL;
            BPRAPAR.NUM7 = BPCOLIBB.PVAL_INFO[7-1].NUM;
            BPRAPAR.PVAL8 = BPCOLIBB.PVAL_INFO[8-1].PVAL;
            BPRAPAR.NUM8 = BPCOLIBB.PVAL_INFO[8-1].NUM;
            BPRAPAR.PVAL9 = BPCOLIBB.PVAL_INFO[9-1].PVAL;
            BPRAPAR.NUM9 = BPCOLIBB.PVAL_INFO[9-1].NUM;
            BPRAPAR.PVAL10 = BPCOLIBB.PVAL_INFO[10-1].PVAL;
            BPRAPAR.NUM10 = BPCOLIBB.PVAL_INFO[10-1].NUM;
            BPRAPAR.PVAL11 = BPCOLIBB.PVAL_INFO[11-1].PVAL;
            BPRAPAR.NUM11 = BPCOLIBB.PVAL_INFO[11-1].NUM;
            BPRAPAR.PVAL12 = BPCOLIBB.PVAL_INFO[12-1].PVAL;
            BPRAPAR.NUM12 = BPCOLIBB.PVAL_INFO[12-1].NUM;
            BPRAPAR.PVAL13 = BPCOLIBB.PVAL_INFO[13-1].PVAL;
            BPRAPAR.NUM13 = BPCOLIBB.PVAL_INFO[13-1].NUM;
            BPRAPAR.PVAL14 = BPCOLIBB.PVAL_INFO[14-1].PVAL;
            BPRAPAR.NUM14 = BPCOLIBB.PVAL_INFO[14-1].NUM;
            BPRAPAR.PVAL15 = BPCOLIBB.PVAL_INFO[15-1].PVAL;
            BPRAPAR.NUM15 = BPCOLIBB.PVAL_INFO[15-1].NUM;
            BPRAPAR.PVAL16 = BPCOLIBB.PVAL_INFO[16-1].PVAL;
            BPRAPAR.NUM16 = BPCOLIBB.PVAL_INFO[16-1].NUM;
            BPRAPAR.PVAL17 = BPCOLIBB.PVAL_INFO[17-1].PVAL;
            BPRAPAR.NUM17 = BPCOLIBB.PVAL_INFO[17-1].NUM;
            BPRAPAR.PVAL18 = BPCOLIBB.PVAL_INFO[18-1].PVAL;
            BPRAPAR.NUM18 = BPCOLIBB.PVAL_INFO[18-1].NUM;
            BPRAPAR.PVAL19 = BPCOLIBB.PVAL_INFO[19-1].PVAL;
            BPRAPAR.NUM19 = BPCOLIBB.PVAL_INFO[19-1].NUM;
            BPRAPAR.PVAL20 = BPCOLIBB.PVAL_INFO[20-1].PVAL;
            BPRAPAR.NUM20 = BPCOLIBB.PVAL_INFO[20-1].NUM;
        }
        if (BPCOLIBB.FLG == '2') {
            T000_WRITE_BPTAPAR();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_BPTAPAR();
            if (pgmRtn) return;
        }
    }
    public void B020_WRITE_BPTALIB() throws IOException,SQLException,Exception {
        R000_TRANS_ALIB_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCSLIBR.POINTER = BPRALIB;
        BPCSLIBR.LEN = 1211;
        BPCSLIBR.FUNC = 'B';
        S000_CALL_BPZSLIBR();
        if (pgmRtn) return;
        if (BPCSLIBR.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ALIB_WRITE_ERR);
        }
    }
    public void B020_WRITE_BPTAPAR() throws IOException,SQLException,Exception {
        R000_TRANS_APAR_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCSLIBR.POINTER = BPRAPAR;
        BPCSLIBR.LEN = 608;
        BPCSLIBR.FUNC = 'R';
        S000_CALL_BPZSLIBR();
        if (pgmRtn) return;
        if (BPCSLIBR.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APAR_WRITE_ERR);
        }
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBR);
        BPCOLIBR.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOLIBR.APP_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOLIBR.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOLIBR.UP_BR = BPCOLIBB.UP_BR;
        BPCOLIBR.APP_NO = BPCOLIBB.APP_NO;
        BPCOLIBR.APP_TYPE = BPCOLIBB.APP_TYPE;
        BPCOLIBR.APP_STS = '0';
        BPCOLIBR.APP_CCY = BPCOLIBB.CCY;
        BPCOLIBR.APP_AMT = BPCOLIBB.APP_AMT;
        BPCOLIBR.ALLOT_TYPE = BPCOLIBB.ALLOT_TP;
        BPCOLIBR.APP_NOTE = BPCOLIBB.APP_NOTE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOLIBB.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOLIBR;
        SCCFMT.DATA_LEN = 168;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R020_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBQ);
        BPCOLIBQ.APP_NO = BPRAPAR.KEY.APP_NO;
        BPCOLIBQ.CCY = BPRAPAR.APP_CCY;
        BPCOLIBQ.APP_AMT = BPRAPAR.APP_AMT;
        BPCOLIBQ.OUT_AMT = BPRAPAR.OUT_AMT;
        CEP.TRC(SCCGWA, BPCOLIBQ.APP_NO);
        BPCOLIBQ.PVAL_INFO[1-1].PVAL = BPRAPAR.PVAL1;
        BPCOLIBQ.PVAL_INFO[1-1].NUM = BPRAPAR.NUM1;
        BPCOLIBQ.PVAL_INFO[2-1].PVAL = BPRAPAR.PVAL2;
        BPCOLIBQ.PVAL_INFO[2-1].NUM = BPRAPAR.NUM2;
        BPCOLIBQ.PVAL_INFO[3-1].PVAL = BPRAPAR.PVAL3;
        BPCOLIBQ.PVAL_INFO[3-1].NUM = BPRAPAR.NUM3;
        BPCOLIBQ.PVAL_INFO[4-1].PVAL = BPRAPAR.PVAL4;
        BPCOLIBQ.PVAL_INFO[4-1].NUM = BPRAPAR.NUM4;
        BPCOLIBQ.PVAL_INFO[5-1].PVAL = BPRAPAR.PVAL5;
        BPCOLIBQ.PVAL_INFO[5-1].NUM = BPRAPAR.NUM5;
        BPCOLIBQ.PVAL_INFO[6-1].PVAL = BPRAPAR.PVAL6;
        BPCOLIBQ.PVAL_INFO[6-1].NUM = BPRAPAR.NUM6;
        BPCOLIBQ.PVAL_INFO[7-1].PVAL = BPRAPAR.PVAL7;
        BPCOLIBQ.PVAL_INFO[7-1].NUM = BPRAPAR.NUM7;
        BPCOLIBQ.PVAL_INFO[8-1].PVAL = BPRAPAR.PVAL8;
        BPCOLIBQ.PVAL_INFO[8-1].NUM = BPRAPAR.NUM8;
        BPCOLIBQ.PVAL_INFO[9-1].PVAL = BPRAPAR.PVAL9;
        BPCOLIBQ.PVAL_INFO[9-1].NUM = BPRAPAR.NUM9;
        BPCOLIBQ.PVAL_INFO[10-1].PVAL = BPRAPAR.PVAL10;
        BPCOLIBQ.PVAL_INFO[10-1].NUM = BPRAPAR.NUM10;
        BPCOLIBQ.PVAL_INFO[11-1].PVAL = BPRAPAR.PVAL11;
        BPCOLIBQ.PVAL_INFO[11-1].NUM = BPRAPAR.NUM11;
        BPCOLIBQ.PVAL_INFO[12-1].PVAL = BPRAPAR.PVAL12;
        BPCOLIBQ.PVAL_INFO[12-1].NUM = BPRAPAR.NUM12;
        BPCOLIBQ.PVAL_INFO[13-1].PVAL = BPRAPAR.PVAL13;
        BPCOLIBQ.PVAL_INFO[13-1].NUM = BPRAPAR.NUM13;
        BPCOLIBQ.PVAL_INFO[14-1].PVAL = BPRAPAR.PVAL14;
        BPCOLIBQ.PVAL_INFO[14-1].NUM = BPRAPAR.NUM14;
        BPCOLIBQ.PVAL_INFO[15-1].PVAL = BPRAPAR.PVAL15;
        BPCOLIBQ.PVAL_INFO[15-1].NUM = BPRAPAR.NUM15;
        BPCOLIBQ.PVAL_INFO[16-1].PVAL = BPRAPAR.PVAL16;
        BPCOLIBQ.PVAL_INFO[16-1].NUM = BPRAPAR.NUM16;
        BPCOLIBQ.PVAL_INFO[17-1].PVAL = BPRAPAR.PVAL17;
        BPCOLIBQ.PVAL_INFO[17-1].NUM = BPRAPAR.NUM17;
        BPCOLIBQ.PVAL_INFO[18-1].PVAL = BPRAPAR.PVAL18;
        BPCOLIBQ.PVAL_INFO[18-1].NUM = BPRAPAR.NUM18;
        BPCOLIBQ.PVAL_INFO[19-1].PVAL = BPRAPAR.PVAL19;
        BPCOLIBQ.PVAL_INFO[19-1].NUM = BPRAPAR.NUM19;
        BPCOLIBQ.PVAL_INFO[20-1].PVAL = BPRAPAR.PVAL20;
        BPCOLIBQ.PVAL_INFO[20-1].NUM = BPRAPAR.NUM20;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOLIBB.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOLIBQ;
        SCCFMT.DATA_LEN = 546;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOALBO);
        BPCOALBO.APP_NO = BPRALIB.KEY.APP_NO;
        BPCOALBO.APP_BR = BPRALIB.APP_BR;
        BPCOALBO.UP_BR = BPRALIB.UP_BR;
        BPCOALBO.ALLOT_TYPE = BPRALIB.ALLOT_TYPE;
        BPCOALBO.APP_TYPE = BPRALIB.APP_TYPE;
        BPCOALBO.APP_STS = BPRALIB.APP_STS;
        BPCOALBO.CONF_NO = BPRALIB.CONF_NO;
        BPCOALBO.APP_TLR = BPRALIB.APP_TLR;
        BPCOALBO.APP_DT = BPRALIB.APP_DT;
        BPCOALBO.ACP_TLR = BPRALIB.ACP_TLR;
        BPCOALBO.ACP_DT = BPRALIB.ACP_DT;
        BPCOALBO.CONF_TLR = BPRALIB.CONF_TLR;
        BPCOALBO.CONF_DT = BPRALIB.CONF_DT;
        BPCOALBO.ADT_TLR = BPRALIB.ADT_TLR;
        BPCOALBO.ADT_DT = BPRALIB.ADT_DT;
        BPCOALBO.REJ_TLR = BPRALIB.REJ_TLR;
        BPCOALBO.REJ_DT = BPRALIB.REJ_DT;
        BPCOALBO.UNDO_TLR = BPRALIB.UNDO_TLR;
        BPCOALBO.UNDO_DT = BPRALIB.UNDO_DT;
        BPCOALBO.BACK_TLR = BPRALIB.BACK_TLR;
        BPCOALBO.BACK_DT = BPRALIB.BACK_DT;
        BPCOALBO.IN_TLR = BPRALIB.IN_TLR;
        BPCOALBO.IN_DT = BPRALIB.IN_DT;
        BPCOALBO.OUT_TLR = BPRALIB.OUT_TLR;
        BPCOALBO.OUT_DT = BPRALIB.OUT_DT;
        BPCOALBO.UPD_TLR = BPRALIB.UPD_TLR;
        BPCOALBO.UPD_DT = BPRALIB.UPD_DT;
        BPCOALBO.MOV_DT = BPRALIB.MOV_DT;
        IBS.init(SCCGWA, BPRAPAR);
        BPRAPAR.KEY.APP_NO = BPRALIB.KEY.APP_NO;
        T000_READ_BPTAPAR();
        if (pgmRtn) return;
        BPCOALBO.APP_CCY = " ";
        BPCOALBO.APP_AMT = 0;
        BPCOALBO.OUT_AMT = 0;
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            BPCOALBO.PVAL_INFO[WS_CNT-1].PVAL = 0;
            BPCOALBO.PVAL_INFO[WS_CNT-1].NUM = 0;
        }
        BPCOALBO.APP_CCY = BPRAPAR.APP_CCY;
        BPCOALBO.APP_AMT = BPRAPAR.APP_AMT;
        BPCOALBO.OUT_AMT = BPRAPAR.OUT_AMT;
        BPCOALBO.PVAL_INFO[1-1].PVAL = BPRAPAR.PVAL1;
        BPCOALBO.PVAL_INFO[1-1].NUM = BPRAPAR.NUM1;
        BPCOALBO.PVAL_INFO[2-1].PVAL = BPRAPAR.PVAL2;
        BPCOALBO.PVAL_INFO[2-1].NUM = BPRAPAR.NUM2;
        BPCOALBO.PVAL_INFO[3-1].PVAL = BPRAPAR.PVAL3;
        BPCOALBO.PVAL_INFO[3-1].NUM = BPRAPAR.NUM3;
        BPCOALBO.PVAL_INFO[4-1].PVAL = BPRAPAR.PVAL4;
        BPCOALBO.PVAL_INFO[4-1].NUM = BPRAPAR.NUM4;
        BPCOALBO.PVAL_INFO[5-1].PVAL = BPRAPAR.PVAL5;
        BPCOALBO.PVAL_INFO[5-1].NUM = BPRAPAR.NUM5;
        BPCOALBO.PVAL_INFO[6-1].PVAL = BPRAPAR.PVAL6;
        BPCOALBO.PVAL_INFO[6-1].NUM = BPRAPAR.NUM6;
        BPCOALBO.PVAL_INFO[7-1].PVAL = BPRAPAR.PVAL7;
        BPCOALBO.PVAL_INFO[7-1].NUM = BPRAPAR.NUM7;
        BPCOALBO.PVAL_INFO[8-1].PVAL = BPRAPAR.PVAL8;
        BPCOALBO.PVAL_INFO[8-1].NUM = BPRAPAR.NUM8;
        BPCOALBO.PVAL_INFO[9-1].PVAL = BPRAPAR.PVAL9;
        BPCOALBO.PVAL_INFO[9-1].NUM = BPRAPAR.NUM9;
        BPCOALBO.PVAL_INFO[10-1].PVAL = BPRAPAR.PVAL10;
        BPCOALBO.PVAL_INFO[10-1].NUM = BPRAPAR.NUM10;
        BPCOALBO.PVAL_INFO[11-1].PVAL = BPRAPAR.PVAL11;
        BPCOALBO.PVAL_INFO[11-1].NUM = BPRAPAR.NUM11;
        BPCOALBO.PVAL_INFO[12-1].PVAL = BPRAPAR.PVAL12;
        BPCOALBO.PVAL_INFO[12-1].NUM = BPRAPAR.NUM12;
        BPCOALBO.PVAL_INFO[13-1].PVAL = BPRAPAR.PVAL13;
        BPCOALBO.PVAL_INFO[13-1].NUM = BPRAPAR.NUM13;
        BPCOALBO.PVAL_INFO[14-1].PVAL = BPRAPAR.PVAL14;
        BPCOALBO.PVAL_INFO[14-1].NUM = BPRAPAR.NUM14;
        BPCOALBO.PVAL_INFO[15-1].PVAL = BPRAPAR.PVAL15;
        BPCOALBO.PVAL_INFO[15-1].NUM = BPRAPAR.NUM15;
        BPCOALBO.PVAL_INFO[16-1].PVAL = BPRAPAR.PVAL16;
        BPCOALBO.PVAL_INFO[16-1].NUM = BPRAPAR.NUM16;
        BPCOALBO.PVAL_INFO[17-1].PVAL = BPRAPAR.PVAL17;
        BPCOALBO.PVAL_INFO[17-1].NUM = BPRAPAR.NUM17;
        BPCOALBO.PVAL_INFO[18-1].PVAL = BPRAPAR.PVAL18;
        BPCOALBO.PVAL_INFO[18-1].NUM = BPRAPAR.NUM18;
        BPCOALBO.PVAL_INFO[19-1].PVAL = BPRAPAR.PVAL19;
        BPCOALBO.PVAL_INFO[19-1].NUM = BPRAPAR.NUM19;
        BPCOALBO.PVAL_INFO[20-1].PVAL = BPRAPAR.PVAL20;
        BPCOALBO.PVAL_INFO[20-1].NUM = BPRAPAR.NUM20;
    }
    public void B010_CHECK_APPNO_BPTALIB_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        IBS.init(SCCGWA, BPCSLIBR);
        BPRALIB.KEY.APP_NO = BPCOLIBB.APP_NO;
        BPCSLIBR.POINTER = BPRALIB;
        BPCSLIBR.LEN = 1211;
        BPCSLIBR.FUNC = 'Q';
        S000_CALL_BPZSLIBR();
        if (pgmRtn) return;
    }
    public void B010_CHECK_APPNO_BPTAPAR_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPAR);
        IBS.init(SCCGWA, BPCSLIBR);
        BPRAPAR.KEY.APP_NO = BPCOLIBB.APP_NO;
        BPCSLIBR.POINTER = BPRAPAR;
        BPCSLIBR.LEN = 608;
        BPCSLIBR.FUNC = 'E';
        S000_CALL_BPZSLIBR();
        if (pgmRtn) return;
    }
    public void B010_ADD_APP_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_BPFBAS_SEQ;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = K_RUN_MODE;
        BPCSGSEQ.NUM = K_NUM;
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        BPCOLIBB.APP_NO = (int) BPCSGSEQ.SEQ;
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void R000_TRANS_ALIB_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        BPRALIB.KEY.APP_NO = BPCOLIBB.APP_NO;
        BPRALIB.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRALIB.UP_BR = BPCOLIBB.UP_BR;
        BPRALIB.ALLOT_TYPE = BPCOLIBB.ALLOT_TP;
        BPRALIB.APP_TYPE = BPCOLIBB.APP_TYPE;
        BPRALIB.APP_STS = '0';
        BPRALIB.APP_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRALIB.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRALIB.APP_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRALIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRALIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRALIB.APP_NOTE = BPCOLIBB.APP_NOTE;
        CEP.TRC(SCCGWA, BPRALIB.APP_NOTE);
        CEP.TRC(SCCGWA, BPRALIB.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRALIB.APP_BR);
        CEP.TRC(SCCGWA, BPRALIB.UP_BR);
        CEP.TRC(SCCGWA, BPRALIB.ALLOT_TYPE);
        CEP.TRC(SCCGWA, BPRALIB.APP_TYPE);
        CEP.TRC(SCCGWA, BPRALIB.APP_STS);
        CEP.TRC(SCCGWA, BPRALIB.APP_TLR);
        CEP.TRC(SCCGWA, BPRALIB.APP_DT);
        CEP.TRC(SCCGWA, BPRALIB.APP_TM);
        CEP.TRC(SCCGWA, BPRALIB.UPD_DT);
        CEP.TRC(SCCGWA, BPRALIB.UPD_TLR);
    }
    public void R000_TRANS_APAR_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPAR);
        BPRAPAR.KEY.APP_NO = BPCOLIBB.APP_NO;
        BPRAPAR.APP_CCY = BPCOLIBB.CCY;
        BPRAPAR.APP_AMT = BPCOLIBB.APP_AMT;
        if (BPCOLIBB.APP_TYPE == '1') {
            BPRAPAR.OUT_AMT = BPCOLIBB.APP_AMT;
        }
        BPRAPAR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRAPAR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRAPAR.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = BPRAPAR.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRAPAR.TS = "0" + BPRAPAR.TS;
        BPRAPAR.PVAL1 = BPCOLIBB.PVAL_INFO[1-1].PVAL;
        BPRAPAR.NUM1 = BPCOLIBB.PVAL_INFO[1-1].NUM;
        BPRAPAR.PVAL2 = BPCOLIBB.PVAL_INFO[2-1].PVAL;
        BPRAPAR.NUM2 = BPCOLIBB.PVAL_INFO[2-1].NUM;
        BPRAPAR.PVAL3 = BPCOLIBB.PVAL_INFO[3-1].PVAL;
        BPRAPAR.NUM3 = BPCOLIBB.PVAL_INFO[3-1].NUM;
        BPRAPAR.PVAL4 = BPCOLIBB.PVAL_INFO[4-1].PVAL;
        BPRAPAR.NUM4 = BPCOLIBB.PVAL_INFO[4-1].NUM;
        BPRAPAR.PVAL5 = BPCOLIBB.PVAL_INFO[5-1].PVAL;
        BPRAPAR.NUM5 = BPCOLIBB.PVAL_INFO[5-1].NUM;
        BPRAPAR.PVAL6 = BPCOLIBB.PVAL_INFO[6-1].PVAL;
        BPRAPAR.NUM6 = BPCOLIBB.PVAL_INFO[6-1].NUM;
        BPRAPAR.PVAL7 = BPCOLIBB.PVAL_INFO[7-1].PVAL;
        BPRAPAR.NUM7 = BPCOLIBB.PVAL_INFO[7-1].NUM;
        BPRAPAR.PVAL8 = BPCOLIBB.PVAL_INFO[8-1].PVAL;
        BPRAPAR.NUM8 = BPCOLIBB.PVAL_INFO[8-1].NUM;
        BPRAPAR.PVAL9 = BPCOLIBB.PVAL_INFO[9-1].PVAL;
        BPRAPAR.NUM9 = BPCOLIBB.PVAL_INFO[9-1].NUM;
        BPRAPAR.PVAL10 = BPCOLIBB.PVAL_INFO[10-1].PVAL;
        BPRAPAR.NUM10 = BPCOLIBB.PVAL_INFO[10-1].NUM;
        BPRAPAR.PVAL11 = BPCOLIBB.PVAL_INFO[11-1].PVAL;
        BPRAPAR.NUM11 = BPCOLIBB.PVAL_INFO[11-1].NUM;
        BPRAPAR.PVAL12 = BPCOLIBB.PVAL_INFO[12-1].PVAL;
        BPRAPAR.NUM12 = BPCOLIBB.PVAL_INFO[12-1].NUM;
        BPRAPAR.PVAL13 = BPCOLIBB.PVAL_INFO[13-1].PVAL;
        BPRAPAR.NUM13 = BPCOLIBB.PVAL_INFO[13-1].NUM;
        BPRAPAR.PVAL14 = BPCOLIBB.PVAL_INFO[14-1].PVAL;
        BPRAPAR.NUM14 = BPCOLIBB.PVAL_INFO[14-1].NUM;
        BPRAPAR.PVAL15 = BPCOLIBB.PVAL_INFO[15-1].PVAL;
        BPRAPAR.NUM15 = BPCOLIBB.PVAL_INFO[15-1].NUM;
        BPRAPAR.PVAL16 = BPCOLIBB.PVAL_INFO[16-1].PVAL;
        BPRAPAR.NUM16 = BPCOLIBB.PVAL_INFO[16-1].NUM;
        BPRAPAR.PVAL17 = BPCOLIBB.PVAL_INFO[17-1].PVAL;
        BPRAPAR.NUM17 = BPCOLIBB.PVAL_INFO[17-1].NUM;
        BPRAPAR.PVAL18 = BPCOLIBB.PVAL_INFO[18-1].PVAL;
        BPRAPAR.NUM18 = BPCOLIBB.PVAL_INFO[18-1].NUM;
        BPRAPAR.PVAL19 = BPCOLIBB.PVAL_INFO[19-1].PVAL;
        BPRAPAR.NUM19 = BPCOLIBB.PVAL_INFO[19-1].NUM;
        BPRAPAR.PVAL20 = BPCOLIBB.PVAL_INFO[20-1].PVAL;
        BPRAPAR.NUM20 = BPCOLIBB.PVAL_INFO[20-1].NUM;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRALIB.BACK_TLR);
        CEP.TRC(SCCGWA, BPRALIB.BACK_DT);
        CEP.TRC(SCCGWA, BPRALIB.BACK_TM);
        CEP.TRC(SCCGWA, BPCOALBO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOLIBB.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOALBO;
        SCCFMT.DATA_LEN = 746;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTAPAR() throws IOException,SQLException,Exception {
        BPTAPAR_RD = new DBParm();
        BPTAPAR_RD.TableName = "BPTAPAR";
        BPTAPAR_RD.where = "APP_NO = :BPRAPAR.KEY.APP_NO";
        BPTAPAR_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPAR, this, BPTAPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APAR_NOTFND);
        }
    }
    public void TOOO_READ_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        BPTALIB_RD.where = "APP_NO = :BPRALIB.KEY.APP_NO";
        BPTALIB_RD.upd = true;
        IBS.READ(SCCGWA, BPRALIB, this, BPTALIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ALIB_NOTFND);
        }
    }
    public void T000_REWRITE_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        IBS.REWRITE(SCCGWA, BPRALIB, BPTALIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ALIB_WRITE_ERR);
        }
    }
    public void T000_WRITE_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        IBS.WRITE(SCCGWA, BPRALIB, BPTALIB_RD);
    }
    public void T000_REWRITE_BPTAPAR() throws IOException,SQLException,Exception {
        BPTAPAR_RD = new DBParm();
        BPTAPAR_RD.TableName = "BPTAPAR";
        IBS.REWRITE(SCCGWA, BPRAPAR, BPTAPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APAR_WRITE_ERR);
        }
    }
    public void T000_WRITE_BPTAPAR() throws IOException,SQLException,Exception {
        BPTAPAR_RD = new DBParm();
        BPTAPAR_RD.TableName = "BPTAPAR";
        IBS.WRITE(SCCGWA, BPRAPAR, BPTAPAR_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
