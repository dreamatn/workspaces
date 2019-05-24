package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSAPLL {
    DBParm BPTAPLI_RD;
    DBParm BPTADTL_RD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm BPTADTL_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_BR_BVAPP_READ = "BP-BR-BVAPP-READ";
    String CPN_BR_BVAPP_BRW = "BP-BR-BVAPP-BRW";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    char K_RUN_MODE = 'O';
    short K_NUM = 1;
    String K_BPFBAS_SEQ = "CASHMOVE";
    String K_SEQ_TYPE = "CMOVE";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    int WS_BR = 0;
    int WS_I = 0;
    int WS_O = 0;
    int WS_D = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    BPZSAPLL_WS_PLBOX_NO WS_PLBOX_NO = new BPZSAPLL_WS_PLBOX_NO();
    char WS_TBL_BANK_FLAG = ' ';
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSPLIR BPCSPLIR = new BPCSPLIR();
    BPRAPLI BPRAPLI = new BPRAPLI();
    BPRADTL BPRADTL = new BPRADTL();
    BPCOAPLO BPCOAPLO = new BPCOAPLO();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCSPLIA BPCSPLIA = new BPCSPLIA();
    BPCSAPBR BPCSAPBR = new BPCSAPBR();
    BPCOPLIR BPCOPLIR = new BPCOPLIR();
    BPCOPLIQ BPCOPLIQ = new BPCOPLIQ();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCOAPOL BPCOAPOL = new BPCOAPOL();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPCOAPLL BPCOAPLL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOAPLL BPCOAPLL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOAPLL = BPCOAPLL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSAPLL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOAPLL.FUNC == 'B') {
            B010_BROWSE_DATA_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOAPLL.FUNC == 'M') {
            B020_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOAPLL.FUNC == 'I') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOAPLL.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR1);
        }
    }
    public void B010_BROWSE_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSPLIA);
        BPCSPLIA.APP_NO = BPCOAPLL.APP_NO;
        BPCSPLIA.APP_BR = BPCOAPLL.APP_BR;
        BPCSPLIA.BR = BPCOAPLL.BR;
        BPCSPLIA.APP_NOTE = BPCOAPLL.APP_NOTE;
        BPCSPLIA.APP_TYPE = BPCOAPLL.APP_TYPE;
        BPCSPLIA.APP_STS = BPCOAPLL.APP_STS;
        BPCSPLIA.BEG_DT = BPCOAPLL.BEG_DT;
        BPCSPLIA.END_DT = BPCOAPLL.END_DT;
        BPCSPLIA.BV_CODE = BPCOAPLL.BV_INFO[1-1].BV_CODE;
        CEP.TRC(SCCGWA, BPCSPLIA.BV_CODE);
        S000_CALL_BPZSPLIA();
        if (pgmRtn) return;
    }
    public void B020_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCOAPLL.MODIFY_STS != 'U' 
            && BPCOAPLL.MODIFY_STS != 'R' 
            && BPCOAPLL.MODIFY_STS != 'A' 
            && BPCOAPLL.MODIFY_STS != 'C' 
            && BPCOAPLL.MODIFY_STS != 'D' 
            && BPCOAPLL.MODIFY_STS != 'Q' 
            && BPCOAPLL.MODIFY_STS != 'K' 
            && BPCOAPLL.MODIFY_STS != 'I') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR2);
        }
        IBS.init(SCCGWA, BPRAPLI);
        CEP.TRC(SCCGWA, BPCOAPLL.MODIFY_STS);
        if (BPCOAPLL.FLG == '2' 
            && BPCOAPLL.MODIFY_STS == 'Q') {
        } else {
            CEP.TRC(SCCGWA, BPCOAPLL.APP_NO);
            BPRAPLI.KEY.APP_NO = BPCOAPLL.APP_NO;
            TOOO_READ_BPTAPLI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "TST002");
            CEP.TRC(SCCGWA, BPRAPLI.KEY.APP_NO);
            CEP.TRC(SCCGWA, BPRAPLI.APP_STS);
            CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
            CEP.TRC(SCCGWA, BPCOAPLL.MODIFY_STS);
            CEP.TRC(SCCGWA, BPRAPLI.APP_STS);
        }
        if (BPCOAPLL.MODIFY_STS == 'U') {
            if (BPRAPLI.APP_STS == '0') {
                BPRAPLI.APP_STS = ' ';
                BPRAPLI.BACK_TLR = " ";
                BPRAPLI.BACK_DT = 0;
                BPRAPLI.BACK_TM = 0;
                BPRAPLI.UPD_TLR = " ";
                BPRAPLI.UPD_DT = 0;
                BPRAPLI.APP_STS = '2';
                BPRAPLI.BACK_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPRAPLI.BACK_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRAPLI.BACK_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                CEP.TRC(SCCGWA, "TEST001");
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR3);
            }
        }
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPCOAPLL.MODIFY_STS == 'R') {
                if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.TRC(SCCGWA, "TEST002");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR4);
                }
                if (BPRAPLI.APP_STS == '0' 
                    || BPRAPLI.APP_STS == '1' 
                    || BPRAPLI.APP_STS == '3' 
                    || BPRAPLI.APP_STS == '4') {
                    BPRAPLI.APP_STS = ' ';
                    BPRAPLI.REJ_TLR = " ";
                    BPRAPLI.REJ_DT = 0;
                    BPRAPLI.REJ_TM = 0;
                    BPRAPLI.UPD_TLR = " ";
                    BPRAPLI.UPD_DT = 0;
                    BPRAPLI.APP_STS = '7';
                    BPRAPLI.REJ_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.REJ_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRAPLI.REJ_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.TRC(SCCGWA, "TEST003");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR5);
                }
            }
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                if (BPCOAPLL.MODIFY_STS == 'R') {
                    if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR6);
                    }
                    if (BPRAPLI.APP_STS == '0') {
                        BPRAPLI.APP_STS = ' ';
                        BPRAPLI.REJ_TLR = " ";
                        BPRAPLI.REJ_DT = 0;
                        BPRAPLI.REJ_TM = 0;
                        BPRAPLI.UPD_TLR = " ";
                        BPRAPLI.UPD_DT = 0;
                        BPRAPLI.APP_STS = '7';
                        BPRAPLI.REJ_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.REJ_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRAPLI.REJ_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR174);
                    }
                }
            }
        }
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPCOAPLL.MODIFY_STS == 'A') {
                CEP.TRC(SCCGWA, BPRAPLI.UP_BR);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
                if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.TRC(SCCGWA, "TEST004");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR8);
                }
                CEP.TRC(SCCGWA, "TST001");
                CEP.TRC(SCCGWA, BPRAPLI.APP_STS);
                if (BPRAPLI.APP_STS == '0') {
                    BPRAPLI.APP_STS = ' ';
                    BPRAPLI.ACP_TLR = " ";
                    BPRAPLI.ACP_DT = 0;
                    BPRAPLI.ACP_TM = 0;
                    BPRAPLI.UPD_TLR = " ";
                    BPRAPLI.UPD_DT = 0;
                    BPRAPLI.APP_STS = '1';
                    BPRAPLI.ACP_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.ACP_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRAPLI.ACP_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CEP.TRC(SCCGWA, BPRAPLI.ACP_TLR);
                    CEP.TRC(SCCGWA, BPRAPLI.ACP_DT);
                    CEP.TRC(SCCGWA, BPRAPLI.ACP_TM);
                } else {
                    CEP.TRC(SCCGWA, "TEST005");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR10);
                }
            }
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                if (BPCOAPLL.MODIFY_STS == 'A') {
                    CEP.TRC(SCCGWA, BPRAPLI.UP_BR);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
                    if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR11);
                    }
                    CEP.TRC(SCCGWA, BPRAPLI.APP_STS);
                    if (BPRAPLI.APP_STS == '5') {
                        BPRAPLI.APP_STS = ' ';
                        BPRAPLI.ACP_TLR = " ";
                        BPRAPLI.ACP_DT = 0;
                        BPRAPLI.ACP_TM = 0;
                        BPRAPLI.UPD_TLR = " ";
                        BPRAPLI.UPD_DT = 0;
                        BPRAPLI.APP_STS = '1';
                        BPRAPLI.ACP_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.ACP_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRAPLI.ACP_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        CEP.TRC(SCCGWA, BPRAPLI.ACP_TLR);
                        CEP.TRC(SCCGWA, BPRAPLI.ACP_DT);
                        CEP.TRC(SCCGWA, BPRAPLI.ACP_TM);
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR13);
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, BPRAPLI.UP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPCOAPLL.MODIFY_STS == 'C') {
                if (BPRAPLI.APP_STS == '4') {
                    if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.TRC(SCCGWA, "TEST006");
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR14);
                    }
                    if (!BPRAPLI.ADT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        CEP.TRC(SCCGWA, "TEST007");
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR15);
                    }
                    BPRAPLI.APP_STS = ' ';
                    BPRAPLI.UNDO_TLR = " ";
                    BPRAPLI.UNDO_DT = 0;
                    BPRAPLI.UNDO_TM = 0;
                    BPRAPLI.UPD_TLR = " ";
                    BPRAPLI.UPD_DT = 0;
                    BPRAPLI.APP_STS = '3';
                    BPRAPLI.UNDO_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.UNDO_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRAPLI.UNDO_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.TRC(SCCGWA, "TEST008");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR16);
                }
            }
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                if (BPCOAPLL.MODIFY_STS == 'C') {
                    if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR170);
                    }
                    if (BPRAPLI.APP_STS != '1' 
                        && BPRAPLI.APP_STS != '4') {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR177);
                    } else {
                        if (BPRAPLI.APP_STS == '1') {
                            if (!BPRAPLI.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR175);
                            }
                            BPRAPLI.APP_STS = ' ';
                            BPRAPLI.UNDO_TLR = " ";
                            BPRAPLI.UNDO_DT = 0;
                            BPRAPLI.UNDO_TM = 0;
                            BPRAPLI.UPD_TLR = " ";
                            BPRAPLI.UPD_DT = 0;
                            BPRAPLI.APP_STS = '5';
                            BPRAPLI.UNDO_TLR = SCCGWA.COMM_AREA.TL_ID;
                            BPRAPLI.UNDO_DT = SCCGWA.COMM_AREA.AC_DATE;
                            BPRAPLI.UNDO_TM = SCCGWA.COMM_AREA.TR_TIME;
                            BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                            BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        } else {
                            if (BPRAPLI.APP_STS == '4') {
                                if (!BPRAPLI.ADT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR176);
                                }
                                BPRAPLI.APP_STS = ' ';
                                BPRAPLI.UNDO_TLR = " ";
                                BPRAPLI.UNDO_DT = 0;
                                BPRAPLI.UNDO_TM = 0;
                                BPRAPLI.UPD_TLR = " ";
                                BPRAPLI.UPD_DT = 0;
                                BPRAPLI.APP_STS = '1';
                                BPRAPLI.UNDO_TLR = SCCGWA.COMM_AREA.TL_ID;
                                BPRAPLI.UNDO_DT = SCCGWA.COMM_AREA.AC_DATE;
                                BPRAPLI.UNDO_TM = SCCGWA.COMM_AREA.TR_TIME;
                                BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                                BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                            }
                        }
                    }
                }
            }
        }
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPCOAPLL.MODIFY_STS == 'D') {
                if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.TRC(SCCGWA, "TEST009");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR17);
                }
                if (BPRAPLI.CONF_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                    || BPRAPLI.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    CEP.TRC(SCCGWA, "TEST010");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR18);
                }
                if (BPRAPLI.APP_STS == '3') {
                    BPRAPLI.APP_STS = ' ';
                    BPRAPLI.ADT_TLR = " ";
                    BPRAPLI.ADT_DT = 0;
                    BPRAPLI.ADT_TM = 0;
                    BPRAPLI.UPD_TLR = " ";
                    BPRAPLI.UPD_DT = 0;
                    BPRAPLI.APP_STS = '4';
                    BPRAPLI.ADT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.ADT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRAPLI.ADT_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.TRC(SCCGWA, "TEST011");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR19);
                }
            }
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                if (BPCOAPLL.MODIFY_STS == 'D') {
                    if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR20);
                    }
                    if (BPRAPLI.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR21);
                    }
                    if (BPRAPLI.APP_STS == '1') {
                        BPRAPLI.APP_STS = ' ';
                        BPRAPLI.ADT_TLR = " ";
                        BPRAPLI.ADT_DT = 0;
                        BPRAPLI.ADT_TM = 0;
                        BPRAPLI.UPD_TLR = " ";
                        BPRAPLI.UPD_DT = 0;
                        BPRAPLI.APP_STS = '4';
                        BPRAPLI.ADT_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.ADT_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRAPLI.ADT_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR22);
                    }
                    IBS.init(SCCGWA, BPRADTL);
                    BPRADTL.KEY.APP_NO = BPRAPLI.KEY.APP_NO;
                    T000_READ_BPTADTL_1();
                    if (pgmRtn) return;
                    BPRADTL.OUT_NUM = 0;
                    BPRADTL.OUT_NUM = BPRADTL.APP_NUM;
                    T000_REWRITE_BPTADTL();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, "SUCCESS");
        CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
        CEP.TRC(SCCGWA, BPCOAPLL.MODIFY_STS);
        if (BPCOAPLL.MODIFY_STS == 'Q') {
            CEP.TRC(SCCGWA, BPCOAPLL.FLG);
            CEP.TRC(SCCGWA, BPCOAPLL.APP_TYPE);
            if (BPCOAPLL.FLG == '2') {
                BPRAPLI.KEY.APP_NO = BPCOAPLL.APP_NO;
                BPRAPLI.APP_BR = BPCOAPLL.APP_BR;
                BPRAPLI.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRAPLI.UP_BR = BPCOAPLL.BR;
                BPRAPLI.APP_STS = '3';
                BPRAPLI.APP_TYPE = BPCOAPLL.APP_TYPE;
                BPRAPLI.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRAPLI.CREATE_TLR = BPCOAPLL.TR_TLR;
                CEP.TRC(SCCGWA, BPRAPLI.KEY.APP_NO);
                CEP.TRC(SCCGWA, BPRAPLI.APP_BR);
                CEP.TRC(SCCGWA, BPRAPLI.UP_BR);
                CEP.TRC(SCCGWA, BPRAPLI.APP_STS);
                CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
                CEP.TRC(SCCGWA, BPRAPLI.CREATE_DT);
                CEP.TRC(SCCGWA, BPRAPLI.CREATE_TLR);
            }
            if (BPRAPLI.APP_TYPE == '0') {
                if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                    && BPCOAPLL.FLG == '2') {
                } else {
                    IBS.init(SCCGWA, BPCFTLRQ);
                    BPCFTLRQ.INFO.TLR = BPCOAPLL.TR_TLR;
                    S000_CALL_BPZFTLRQ();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
                    if (BPCFTLRQ.INFO.NEW_BR != BPRAPLI.UP_BR) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR138);
                    }
                    CEP.TRC(SCCGWA, BPRAPLI.ACP_TLR);
                    CEP.TRC(SCCGWA, BPCOAPLL.TR_TLR);
                    if (!BPRAPLI.ACP_TLR.equalsIgnoreCase(BPCOAPLL.TR_TLR)) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR24);
                    }
                }
                CEP.TRC(SCCGWA, "APLISTS2");
                if (BPRAPLI.APP_STS == '1' 
                    || BPRAPLI.APP_STS == '3') {
                    CEP.TRC(SCCGWA, "APLISTS3");
                    BPRAPLI.APP_STS = ' ';
                    BPRAPLI.CONF_TLR = " ";
                    BPRAPLI.CONF_DT = 0;
                    BPRAPLI.CONF_TM = 0;
                    BPRAPLI.UPD_TLR = " ";
                    BPRAPLI.UPD_DT = 0;
                    BPRAPLI.APP_STS = '3';
                    BPRAPLI.CONF_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.CONF_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRAPLI.CONF_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    CEP.TRC(SCCGWA, "TEST014");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR25);
                }
            } else {
                if (BPRAPLI.APP_TYPE == '1') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR92);
                }
            }
        }
        CEP.TRC(SCCGWA, BPRAPLI.APP_STS);
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPCOAPLL.MODIFY_STS == 'K') {
                if (BPRAPLI.APP_STS == '4') {
                    if (BPRAPLI.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                        || BPRAPLI.CONF_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                        || BPRAPLI.ADT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                        || BPRAPLI.UNDO_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        CEP.TRC(SCCGWA, "TEST015");
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR26);
                    }
                    BPRAPLI.CONF_NO = 0;
                    BPRAPLI.APP_STS = ' ';
                    BPRAPLI.OUT_TLR = " ";
                    BPRAPLI.OUT_DT = 0;
                    BPRAPLI.OUT_TM = 0;
                    BPRAPLI.UPD_TLR = " ";
                    BPRAPLI.UPD_DT = 0;
                    CEP.TRC(SCCGWA, BPCOAPLL.CONF_SEQ);
                    BPRAPLI.CONF_NO = BPCOAPLL.CONF_SEQ;
                    BPRAPLI.APP_STS = '5';
                    BPRAPLI.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.OUT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRAPLI.OUT_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CEP.TRC(SCCGWA, BPRAPLI.CONF_NO);
                    T000_REWRITE_BPTAPLI();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "TEST016");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR27);
                }
            }
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                if (BPCOAPLL.MODIFY_STS == 'K') {
                    if (BPRAPLI.APP_STS == '0') {
                        BPRAPLI.CONF_NO = 0;
                        BPRAPLI.APP_STS = ' ';
                        BPRAPLI.OUT_TLR = " ";
                        BPRAPLI.OUT_DT = 0;
                        BPRAPLI.OUT_TM = 0;
                        BPRAPLI.UPD_TLR = " ";
                        BPRAPLI.UPD_DT = 0;
                        CEP.TRC(SCCGWA, BPCOAPLL.CONF_SEQ);
                        BPRAPLI.CONF_NO = BPCOAPLL.CONF_SEQ;
                        BPRAPLI.APP_STS = '5';
                        BPRAPLI.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.OUT_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRAPLI.OUT_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        CEP.TRC(SCCGWA, BPRAPLI.CONF_NO);
                        T000_REWRITE_BPTAPLI();
                        if (pgmRtn) return;
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR28);
                    }
                }
            }
        }
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPCOAPLL.MODIFY_STS == 'I') {
                if (BPRAPLI.APP_STS == '5') {
                    BPRAPLI.APP_STS = ' ';
                    BPRAPLI.IN_TLR = " ";
                    BPRAPLI.IN_DT = 0;
                    BPRAPLI.IN_TM = 0;
                    BPRAPLI.UPD_TLR = " ";
                    BPRAPLI.UPD_DT = 0;
                    BPRAPLI.APP_STS = '6';
                    BPRAPLI.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.IN_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRAPLI.IN_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    T000_REWRITE_BPTAPLI();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "TEST017");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR29);
                }
            }
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                if (BPCOAPLL.MODIFY_STS == 'I') {
                    if (BPRAPLI.APP_STS == '4') {
                        BPRAPLI.APP_STS = ' ';
                        BPRAPLI.IN_TLR = " ";
                        BPRAPLI.IN_DT = 0;
                        BPRAPLI.IN_TM = 0;
                        BPRAPLI.UPD_TLR = " ";
                        BPRAPLI.UPD_DT = 0;
                        BPRAPLI.APP_STS = '6';
                        BPRAPLI.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.IN_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRAPLI.IN_TM = SCCGWA.COMM_AREA.TR_TIME;
                        BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        T000_REWRITE_BPTAPLI();
                        if (pgmRtn) return;
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR32);
                    }
                }
            }
        }
        if (BPCOAPLL.MODIFY_STS == 'Q' 
            && BPCOAPLL.FLG == '2') {
            T000_WRITE_BPTAPLI();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_BPTAPLI();
            if (pgmRtn) return;
        }
        if (BPCOAPLL.MODIFY_STS == 'Q') {
            R000_MODIFY_BPTADTL();
            if (pgmRtn) return;
            R000_TRANS_DATA_PARAMETER_1();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT_1();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "TST999");
        CEP.TRC(SCCGWA, BPCOAPLL.MODIFY_STS);
        CEP.TRC(SCCGWA, BPCOAPLL.CNT);
        if (BPCOAPLL.MODIFY_STS != 'Q' 
            && BPCOAPLL.CNT < 2) {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR141);
        }
    }
    public void T000_WRITE_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        IBS.WRITE(SCCGWA, BPRAPLI, BPTAPLI_RD);
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRADTL);
        BPRADTL.KEY.APP_NO = BPCOAPLL.APP_NO;
        BPRADTL.KEY.BV_CODE = BPCOAPLL.BV_INFO[1-1].BV_CODE;
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
        T000_READ_BPTADTL();
        if (pgmRtn) return;
        R020_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        B010_ADD_APP_NO();
        if (pgmRtn) return;
        B010_CHECK_APPNO_BPTAPLI_EXIST();
        if (pgmRtn) return;
        if (BPCSPLIR.RETURN_INFO == 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APPNO_EXIST);
        }
        B010_CHECK_APPNO_BPTADTL_EXIST();
        if (pgmRtn) return;
        if (BPCSPLIR.RETURN_INFO == 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APPNO_EXIST);
        }
        B020_WRITE_BPTAPLI();
        if (pgmRtn) return;
        R000_TRANS_ADTL_DATA_PARAMETER();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSPLIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BR_BVAPP_BRW, BPCSPLIA);
    }
    public void R000_MODIFY_BPTADTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRADTL);
        if (BPCOAPLL.FLG == '2') {
            BPRADTL.KEY.APP_NO = BPCOAPLL.APP_NO;
            BPRADTL.STS = '0';
            BPRADTL.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRADTL.CREATE_TLR = BPCOAPLL.TR_TLR;
            BPRADTL.OWNER_BK = BPCOAPLL.BR;
            BPRADTL.KEY.BV_CODE = BPCOAPLL.BV_INFO[1-1].BV_CODE;
            BPRADTL.OUT_NUM = BPCOAPLL.BV_INFO[1-1].OUT_NUM;
            BPRADTL.BEG_NO1 = BPCOAPLL.BV_INFO[1-1].BEG_NO1;
            BPRADTL.END_NO1 = BPCOAPLL.BV_INFO[1-1].END_NO1;
            BPRADTL.NUM1 = BPCOAPLL.BV_INFO[1-1].NUM1;
            BPRADTL.BEG_NO2 = BPCOAPLL.BV_INFO[1-1].BEG_NO2;
            BPRADTL.END_NO2 = BPCOAPLL.BV_INFO[1-1].END_NO2;
            BPRADTL.NUM2 = BPCOAPLL.BV_INFO[1-1].NUM2;
            BPRADTL.BEG_NO3 = BPCOAPLL.BV_INFO[1-1].BEG_NO3;
            BPRADTL.END_NO3 = BPCOAPLL.BV_INFO[1-1].END_NO3;
            BPRADTL.NUM3 = BPCOAPLL.BV_INFO[1-1].NUM3;
            BPRADTL.BEG_NO4 = BPCOAPLL.BV_INFO[1-1].BEG_NO4;
            BPRADTL.END_NO4 = BPCOAPLL.BV_INFO[1-1].END_NO4;
            BPRADTL.NUM4 = BPCOAPLL.BV_INFO[1-1].NUM4;
        } else {
            BPRADTL.KEY.APP_NO = BPCOAPLL.APP_NO;
            T000_STARTBR_BPTADTL();
            if (pgmRtn) return;
            T000_READNEXT_BPTADTL();
            if (pgmRtn) return;
            for (WS_CNT = 1; WS_TBL_FLAG != 'N' 
                && WS_CNT <= 10 
                && BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE.trim().length() != 0 
                && !BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE.equalsIgnoreCase("0"); WS_CNT += 1) {
                CEP.TRC(SCCGWA, WS_CNT);
                IBS.init(SCCGWA, BPRADTL);
                BPRADTL.KEY.APP_NO = BPCOAPLL.APP_NO;
                BPRADTL.KEY.BV_CODE = BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE;
                T000_READ_BPTADTL();
                if (pgmRtn) return;
                BPRADTL.OUT_NUM = BPCOAPLL.BV_INFO[WS_CNT-1].OUT_NUM;
                BPRADTL.HEAD_NO1 = BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO1;
                BPRADTL.BEG_NO1 = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO1;
                BPRADTL.END_NO1 = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO1;
                BPRADTL.NUM1 = BPCOAPLL.BV_INFO[WS_CNT-1].NUM1;
                BPRADTL.HEAD_NO2 = BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO2;
                BPRADTL.BEG_NO2 = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO2;
                BPRADTL.END_NO2 = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO2;
                BPRADTL.NUM2 = BPCOAPLL.BV_INFO[WS_CNT-1].NUM2;
                BPRADTL.HEAD_NO3 = BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO3;
                BPRADTL.BEG_NO3 = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO3;
                BPRADTL.END_NO3 = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO3;
                BPRADTL.NUM3 = BPCOAPLL.BV_INFO[WS_CNT-1].NUM3;
                BPRADTL.HEAD_NO4 = BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO4;
                BPRADTL.BEG_NO4 = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO4;
                BPRADTL.END_NO4 = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO4;
                BPRADTL.NUM4 = BPCOAPLL.BV_INFO[WS_CNT-1].NUM4;
                BPRADTL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRADTL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, BPRADTL.BEG_NO1);
                CEP.TRC(SCCGWA, BPRADTL.END_NO1);
                CEP.TRC(SCCGWA, BPRADTL.NUM1);
                CEP.TRC(SCCGWA, BPRADTL.BEG_NO2);
                CEP.TRC(SCCGWA, BPRADTL.END_NO2);
                CEP.TRC(SCCGWA, BPRADTL.NUM2);
                CEP.TRC(SCCGWA, BPRADTL.BEG_NO3);
                CEP.TRC(SCCGWA, BPRADTL.END_NO3);
                CEP.TRC(SCCGWA, BPRADTL.NUM3);
                CEP.TRC(SCCGWA, BPRADTL.BEG_NO4);
                CEP.TRC(SCCGWA, BPRADTL.END_NO4);
                CEP.TRC(SCCGWA, BPRADTL.NUM4);
                T000_REWRITE_BPTADTL();
                if (pgmRtn) return;
                T000_READNEXT_BPTADTL();
                if (pgmRtn) return;
            }
            T000_ENDBR_BPTADTL();
            if (pgmRtn) return;
        }
        if (BPCOAPLL.FLG == '2') {
            T000_WRITE_BPTADTL();
            if (pgmRtn) return;
        }
    }
    public void B020_WRITE_BPTAPLI() throws IOException,SQLException,Exception {
        R000_TRANS_APLI_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCSPLIR.POINTER = BPRAPLI;
        BPCSPLIR.LEN = 1197;
        BPCSPLIR.FUNC = 'B';
        S000_CALL_BPZSPLIR();
        if (pgmRtn) return;
        if (BPCSPLIR.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APLI_WRITE_ERR);
        }
    }
    public void T000_WRITE_BPTADTL() throws IOException,SQLException,Exception {
        BPTADTL_RD = new DBParm();
        BPTADTL_RD.TableName = "BPTADTL";
        IBS.WRITE(SCCGWA, BPRADTL, BPTADTL_RD);
    }
    public void B020_WRITE_BPTADTL() throws IOException,SQLException,Exception {
        BPCSPLIR.POINTER = BPRADTL;
        BPCSPLIR.LEN = 334;
        BPCSPLIR.FUNC = 'R';
        S000_CALL_BPZSPLIR();
        if (pgmRtn) return;
        if (BPCSPLIR.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ADTL_WRITE_ERR);
        }
    }
    public void R010_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOPLIR);
        BPCOPLIR.BR = BPCOAPLL.BR;
        BPCOPLIR.APP_STS = '0';
        BPCOPLIR.APP_TLR = SCCGWA.COMM_AREA.TL_ID;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE.trim().length() != 0; WS_CNT += 1) {
            BPCOPLIR.BV_INFO[WS_CNT-1].APP_NO = BPCOAPLL.APP_NO;
            BPCOPLIR.BV_INFO[WS_CNT-1].BV_CODE = BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE;
            BPCOPLIR.BV_INFO[WS_CNT-1].APP_NUM = BPCOAPLL.BV_INFO[WS_CNT-1].APP_NUM;
        }
        BPCOPLIR.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOPLIR.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOPLIR.APP_NOTE = BPCOAPLL.APP_NOTE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOAPLL.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOPLIR;
        SCCFMT.DATA_LEN = 379;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R020_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOPLIQ);
        BPCOPLIQ.APP_NO = BPRADTL.KEY.APP_NO;
        if (BPRADTL.KEY.BV_CODE.trim().length() > 0 
            || !BPRADTL.KEY.BV_CODE.equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
            BPCOPLIQ.BV_CODE = BPRADTL.KEY.BV_CODE;
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCOPLIQ.BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            BPCOPLIQ.BV_CNM = BPCFBVQU.TX_DATA.BV_CNM;
            BPCOPLIQ.STS = BPRADTL.STS;
            CEP.TRC(SCCGWA, BPCOPLIQ.STS);
            BPCOPLIQ.APP_NUM = BPRADTL.APP_NUM;
            BPCOPLIQ.OUT_NUM = BPRADTL.OUT_NUM;
            BPCOPLIQ.HEAD_NO1 = BPRADTL.HEAD_NO1;
            BPCOPLIQ.BEG_NO1 = BPRADTL.BEG_NO1;
            BPCOPLIQ.END_NO1 = BPRADTL.END_NO1;
            BPCOPLIQ.NUM1 = BPRADTL.NUM1;
            BPCOPLIQ.HEAD_NO2 = BPRADTL.HEAD_NO2;
            BPCOPLIQ.BEG_NO2 = BPRADTL.BEG_NO2;
            BPCOPLIQ.END_NO2 = BPRADTL.END_NO2;
            BPCOPLIQ.NUM2 = BPRADTL.NUM2;
            BPCOPLIQ.HEAD_NO3 = BPRADTL.HEAD_NO3;
            BPCOPLIQ.BEG_NO3 = BPRADTL.BEG_NO3;
            BPCOPLIQ.END_NO3 = BPRADTL.END_NO3;
            BPCOPLIQ.NUM3 = BPRADTL.NUM3;
            BPCOPLIQ.HEAD_NO4 = BPRADTL.HEAD_NO4;
            BPCOPLIQ.BEG_NO4 = BPRADTL.BEG_NO4;
            BPCOPLIQ.END_NO4 = BPRADTL.END_NO4;
            BPCOPLIQ.NUM4 = BPRADTL.NUM4;
            CEP.TRC(SCCGWA, BPCOPLIQ.APP_NUM);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = BPCOAPLL.OUTPUT_FMT;
            SCCFMT.DATA_PTR = BPCOPLIQ;
            SCCFMT.DATA_LEN = 431;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPLO);
        BPCOAPLO.APP_NO = BPRAPLI.KEY.APP_NO;
        BPCOAPLO.BR = BPRAPLI.UP_BR;
        CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
        BPCOAPLO.APP_TYPE = BPRAPLI.APP_TYPE;
        BPCOAPLO.APP_BR = BPRAPLI.APP_BR;
        BPCOAPLO.APP_STS = BPRAPLI.APP_STS;
        BPCOAPLO.CONF_NO = BPRAPLI.CONF_NO;
        BPCOAPLO.APP_DT = BPRAPLI.APP_DT;
        BPCOAPLO.APP_TLR = BPRAPLI.APP_TLR;
        BPCOAPLO.ACP_TLR = BPRAPLI.ACP_TLR;
        BPCOAPLO.ACP_DT = BPRAPLI.ACP_DT;
        BPCOAPLO.CONF_TLR = BPRAPLI.CONF_TLR;
        BPCOAPLO.CONF_DT = BPRAPLI.CONF_DT;
        BPCOAPLO.ADT_TLR = BPRAPLI.ADT_TLR;
        BPCOAPLO.ADT_DT = BPRAPLI.ADT_DT;
        BPCOAPLO.REJ_TLR = BPRAPLI.REJ_TLR;
        BPCOAPLO.REJ_DT = BPRAPLI.REJ_DT;
        BPCOAPLO.UNDO_TLR = BPRAPLI.UNDO_TLR;
        BPCOAPLO.UNDO_DT = BPRAPLI.UNDO_DT;
        BPCOAPLO.BACK_TLR = BPRAPLI.BACK_TLR;
        BPCOAPLO.BACK_DT = BPRAPLI.BACK_DT;
        BPCOAPLO.IN_TLR = BPRAPLI.IN_TLR;
        BPCOAPLO.IN_DT = BPRAPLI.IN_DT;
        BPCOAPLO.OUT_TLR = BPRAPLI.OUT_TLR;
        BPCOAPLO.OUT_DT = BPRAPLI.OUT_DT;
        BPCOAPLO.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOAPLO.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, BPRADTL);
        BPRADTL.KEY.APP_NO = BPRAPLI.KEY.APP_NO;
        T000_READ_BPTADTL_1();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPRADTL.KEY.BV_CODE.trim().length() != 0; WS_CNT += 1) {
            BPCOAPLO.BV_CODE = BPRADTL.KEY.BV_CODE;
            BPCOAPLO.APP_NUM = BPRADTL.APP_NUM;
        }
    }
    public void R000_TRANS_DATA_PARAMETER_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPOL);
        BPCOAPOL.APP_NO = BPRAPLI.KEY.APP_NO;
        BPCOAPOL.BR = BPRAPLI.UP_BR;
        BPCOAPOL.APP_TYPE = '0';
        BPCOAPOL.APP_BR = BPRAPLI.APP_BR;
        BPCOAPOL.APP_STS = BPRAPLI.APP_STS;
        BPCOAPOL.CONF_NO = BPRAPLI.CONF_NO;
        BPCOAPOL.APP_DT = BPRAPLI.APP_DT;
        BPCOAPOL.APP_TLR = BPRAPLI.APP_TLR;
        BPCOAPOL.ACP_TLR = BPRAPLI.ACP_TLR;
        BPCOAPOL.ACP_DT = BPRAPLI.ACP_DT;
        BPCOAPOL.CONF_TLR = BPRAPLI.CONF_TLR;
        BPCOAPOL.CONF_DT = BPRAPLI.CONF_DT;
        BPCOAPOL.ADT_TLR = BPRAPLI.ADT_TLR;
        BPCOAPOL.ADT_DT = BPRAPLI.ADT_DT;
        BPCOAPOL.REJ_TLR = BPRAPLI.REJ_TLR;
        BPCOAPOL.REJ_DT = BPRAPLI.REJ_DT;
        BPCOAPOL.UNDO_TLR = BPRAPLI.UNDO_TLR;
        BPCOAPOL.UNDO_DT = BPRAPLI.UNDO_DT;
        BPCOAPOL.BACK_TLR = BPRAPLI.BACK_TLR;
        BPCOAPOL.BACK_DT = BPRAPLI.BACK_DT;
        BPCOAPOL.IN_TLR = BPRAPLI.IN_TLR;
        BPCOAPOL.IN_DT = BPRAPLI.IN_DT;
        BPCOAPOL.OUT_TLR = BPRAPLI.OUT_TLR;
        BPCOAPOL.OUT_DT = BPRAPLI.OUT_DT;
        BPCOAPOL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOAPOL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, BPRADTL);
        BPRADTL.KEY.APP_NO = BPRAPLI.KEY.APP_NO;
        T000_READ_BPTADTL_1();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPRADTL.KEY.BV_CODE.trim().length() != 0; WS_CNT += 1) {
            BPCOAPOL.BV_INFO[WS_CNT-1].BV_CODE = BPRADTL.KEY.BV_CODE;
            BPCOAPOL.BV_INFO[WS_CNT-1].APP_NUM = BPRADTL.APP_NUM;
        }
    }
    public void B010_CHECK_APPNO_BPTAPLI_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        IBS.init(SCCGWA, BPCSPLIR);
        BPRAPLI.KEY.APP_NO = BPCOAPLL.APP_NO;
        BPCSPLIR.POINTER = BPRAPLI;
        BPCSPLIR.LEN = 1197;
        BPCSPLIR.FUNC = 'Q';
        S000_CALL_BPZSPLIR();
        if (pgmRtn) return;
    }
    public void B010_CHECK_APPNO_BPTADTL_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRADTL);
        IBS.init(SCCGWA, BPCSPLIR);
        BPRADTL.KEY.APP_NO = BPCOAPLL.APP_NO;
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        BPCSPLIR.POINTER = BPRADTL;
        BPCSPLIR.LEN = 334;
        CEP.TRC(SCCGWA, BPCSPLIR.LEN);
        BPCSPLIR.FUNC = 'E';
        S000_CALL_BPZSPLIR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZSPLIR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BR_BVAPP_READ, BPCSPLIR);
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
        BPCOAPLL.APP_NO = (int) BPCSGSEQ.SEQ;
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void R000_TRANS_APLI_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        BPRAPLI.KEY.APP_NO = BPCOAPLL.APP_NO;
        BPRAPLI.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRAPLI.UP_BR = BPCOAPLL.BR;
        BPRAPLI.APP_NOTE = BPCOAPLL.APP_NOTE;
        BPRAPLI.APP_TYPE = BPCOAPLL.APP_TYPE;
        BPRAPLI.APP_STS = '0';
        BPRAPLI.APP_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRAPLI.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRAPLI.APP_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRAPLI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRAPLI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_TRANS_ADTL_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRADTL);
        BPRADTL.KEY.APP_NO = BPCOAPLL.APP_NO;
        BPRADTL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRADTL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRADTL.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = BPRADTL.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRADTL.TS = "0" + BPRADTL.TS;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE.trim().length() != 0 
            && !BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE.equalsIgnoreCase("0"); WS_CNT += 1) {
            BPRADTL.KEY.BV_CODE = BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE;
            CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
            CEP.TRC(SCCGWA, WS_CNT);
            BPRADTL.APP_NUM = BPCOAPLL.BV_INFO[WS_CNT-1].APP_NUM;
            BPRADTL.OUT_NUM = BPCOAPLL.BV_INFO[WS_CNT-1].OUT_NUM;
            BPRADTL.STS = BPCOAPLL.BV_INFO[WS_CNT-1].STS;
            BPRADTL.HEAD_NO1 = BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO1;
            BPRADTL.BEG_NO1 = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO1;
            BPRADTL.END_NO1 = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO1;
            BPRADTL.NUM1 = BPCOAPLL.BV_INFO[WS_CNT-1].NUM1;
            BPRADTL.HEAD_NO2 = BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO2;
            BPRADTL.BEG_NO2 = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO2;
            BPRADTL.END_NO2 = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO2;
            BPRADTL.NUM2 = BPCOAPLL.BV_INFO[WS_CNT-1].NUM2;
            BPRADTL.HEAD_NO3 = BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO3;
            BPRADTL.BEG_NO3 = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO3;
            BPRADTL.END_NO3 = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO3;
            BPRADTL.NUM3 = BPCOAPLL.BV_INFO[WS_CNT-1].NUM3;
            BPRADTL.HEAD_NO4 = BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO4;
            BPRADTL.BEG_NO4 = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO4;
            BPRADTL.END_NO4 = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO4;
            BPRADTL.NUM4 = BPCOAPLL.BV_INFO[WS_CNT-1].NUM4;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPRADTL.BEG_NO1);
            CEP.TRC(SCCGWA, BPRADTL.END_NO1);
            B020_WRITE_BPTADTL();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CNT);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOAPLL.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOAPLO;
        SCCFMT.DATA_LEN = 501;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_OUTPUT_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOAPLL.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOAPOL;
        SCCFMT.DATA_LEN = 557;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTADTL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
        BPTADTL_RD = new DBParm();
        BPTADTL_RD.TableName = "BPTADTL";
        BPTADTL_RD.where = "APP_NO = :BPRADTL.KEY.APP_NO "
            + "AND BV_CODE = :BPRADTL.KEY.BV_CODE";
        BPTADTL_RD.upd = true;
        IBS.READ(SCCGWA, BPRADTL, this, BPTADTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ADTL_NOTFND);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void T000_READ_BPTADTL_1() throws IOException,SQLException,Exception {
        BPTADTL_RD = new DBParm();
        BPTADTL_RD.TableName = "BPTADTL";
        BPTADTL_RD.where = "APP_NO = :BPRADTL.KEY.APP_NO";
        BPTADTL_RD.upd = true;
        IBS.READ(SCCGWA, BPRADTL, this, BPTADTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ADTL_NOTFND);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void TOOO_READ_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        BPTAPLI_RD.where = "APP_NO = :BPRAPLI.KEY.APP_NO";
        BPTAPLI_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPLI, this, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APLI_NOTFND);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void T000_REWRITE_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        IBS.REWRITE(SCCGWA, BPRAPLI, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APLI_WRITE_ERR);
        }
    }
    public void T000_REWRITE_BPTADTL() throws IOException,SQLException,Exception {
        BPTADTL_RD = new DBParm();
        BPTADTL_RD.TableName = "BPTADTL";
        IBS.REWRITE(SCCGWA, BPRADTL, BPTADTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ADTL_WRITE_ERR);
        }
    }
    public void T000_REWRITE_BPTVHPB() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        IBS.REWRITE(SCCGWA, BPRAPLI, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APLI_WRITE_ERR);
        }
    }
    public void T000_STARTBR_BPTADTL() throws IOException,SQLException,Exception {
        BPTADTL_BR.rp = new DBParm();
        BPTADTL_BR.rp.TableName = "BPTADTL";
        BPTADTL_BR.rp.where = "APP_NO = :BPRADTL.KEY.APP_NO";
        IBS.STARTBR(SCCGWA, BPRADTL, this, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTADTL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRADTL, this, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTADTL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
