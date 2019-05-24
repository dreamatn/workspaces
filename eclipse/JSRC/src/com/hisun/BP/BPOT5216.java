package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5216 {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTINTR_BR = new brParm();
    DBParm BPTINTR_RD;
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPL01";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_FRZ_FLG = ' ';
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    int WS_SET_DT = 0;
    int WS_SET_TM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPC5216 BPC5216 = new BPC5216();
    BPRINTR BPRINTR = new BPRINTR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPB5216_AWA_5216 BPB5216_AWA_5216;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT5216 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5216_AWA_5216>");
        BPB5216_AWA_5216 = (BPB5216_AWA_5216) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5216_AWA_5216.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.HQT_BANK);
        if (BPB5216_AWA_5216.BR == ' ' 
            || BPB5216_AWA_5216.BR == 0) {
            BPB5216_AWA_5216.BR = SCCGWA.COMM_AREA.HQT_BANK;
        } else {
            if (BPB5216_AWA_5216.BR != 999999) {
                WS_BR = BPB5216_AWA_5216.BR;
                R000_CHECK_BRANCH();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPB5216_AWA_5216.CCY);
        if (BPB5216_AWA_5216.CCY.trim().length() > 0) {
            WS_CCY = BPB5216_AWA_5216.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_END_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_I = 1;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, WS_END_DT);
        IBS.init(SCCGWA, BPC5216);
        if (BPB5216_AWA_5216.CCY.equalsIgnoreCase("156")) {
            B040_INTR_INFO1();
            if (pgmRtn) return;
        } else {
            B040_INTR_INF11();
            if (pgmRtn) return;
        }
        B060_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "C01";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B040_INTR_INFO2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "C02";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B040_INTR_INFO3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "C03";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B040_INTR_INFO4() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "C04";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B040_INTR_INFO5() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "C05";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B040_INTR_INFO6() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "C06";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B040_INTR_INFO8() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "C08";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B040_INTR_INFO9() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "C09";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B040_INTR_INF11() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPB5216_AWA_5216.BR;
        BPRINTR.KEY.CCY = BPB5216_AWA_5216.CCY;
        BPRINTR.KEY.BASE_TYP = "G01";
        T000_READFIRST_DT();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            if (BPRINTR.KEY.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPRINTR.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                } else {
                    BPRINTR.KEY.BR = BPCPQORG.BRANCH_BR;
                    T000_READFIRST_DT();
                    if (pgmRtn) return;
                    if (WS_FRZ_FLG == 'N') {
                        BPRINTR.KEY.BR = 999999;
                        T000_READFIRST_DT();
                        if (pgmRtn) return;
                        if (WS_FRZ_FLG == 'N') {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
                        } else {
                            B070_01_BROW_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        B070_01_BROW_RECORD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            B070_01_BROW_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPC5216;
        SCCFMT.DATA_LEN = 1850;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_01_BROW_RECORD() throws IOException,SQLException,Exception {
        T000_STARTBR_DT();
        if (pgmRtn) return;
        T000_READNEXT_BPTINTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BBB");
        CEP.TRC(SCCGWA, BPRINTR.KEY.BR);
        CEP.TRC(SCCGWA, BPRINTR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRINTR.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRINTR.RATE);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, WS_END_DT);
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            T000_READNEXT_BPTINTR();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTINTR();
        if (pgmRtn) return;
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        BPC5216.TIMES[WS_I-1].BR = BPRINTR.KEY.BR;
        BPC5216.TIMES[WS_I-1].CCY = BPRINTR.KEY.CCY;
        BPC5216.TIMES[WS_I-1].BASE_TYP = BPRINTR.KEY.BASE_TYP;
        BPC5216.TIMES[WS_I-1].TENOR = BPRINTR.KEY.TENOR;
        BPC5216.TIMES[WS_I-1].RATE = BPRINTR.RATE;
        BPC5216.TIMES[WS_I-1].EFF_DATE = BPRINTR.KEY.DT;
        CEP.TRC(SCCGWA, BPC5216.TIMES[WS_I-1].BR);
        CEP.TRC(SCCGWA, BPC5216.TIMES[WS_I-1].CCY);
        WS_I = (short) (WS_I + 1);
        CEP.TRC(SCCGWA, WS_I);
    }
    public void T000_STARTBR_DT() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR "
            + "AND CCY = :BPRINTR.KEY.CCY "
            + "AND BASE_TYP = :BPRINTR.KEY.BASE_TYP "
            + "AND DT <= :WS_END_DT";
        BPTINTR_BR.rp.order = "DT,TM";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
        CEP.TRC(SCCGWA, "AAA");
        CEP.TRC(SCCGWA, BPRINTR.KEY.BR);
        CEP.TRC(SCCGWA, BPRINTR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRINTR.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRINTR.RATE);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, WS_END_DT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTINTR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTINTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPRINTR, this, BPTINTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTINTR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTINTR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTINTR_BR);
    }
    public void T000_READFIRST_DT() throws IOException,SQLException,Exception {
        BPTINTR_RD = new DBParm();
        BPTINTR_RD.TableName = "BPTINTR";
        BPTINTR_RD.where = "BR = :BPRINTR.KEY.BR "
            + "AND CCY = :BPRINTR.KEY.CCY "
            + "AND BASE_TYP = :BPRINTR.KEY.BASE_TYP "
            + "AND DT <= :WS_END_DT";
        BPTINTR_RD.fst = true;
        IBS.READ(SCCGWA, BPRINTR, this, BPTINTR_RD);
        CEP.TRC(SCCGWA, "AAA");
        CEP.TRC(SCCGWA, BPRINTR.KEY.BR);
        CEP.TRC(SCCGWA, BPRINTR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRINTR.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRINTR.RATE);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, WS_END_DT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTINTR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND);
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB5216_AWA_5216.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = BPB5216_AWA_5216.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
