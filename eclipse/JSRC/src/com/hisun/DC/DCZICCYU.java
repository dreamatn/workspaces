package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZICCYU {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTIACCY_RD;
    boolean pgmRtn = false;
    char WS_IACCY_REC_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIACCY DCRIACCY = new DCRIACCY();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCICCYU DCCICCYU;
    public void MP(SCCGWA SCCGWA, DCCICCYU DCCICCYU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCICCYU = DCCICCYU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZICCYU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCICCYU.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_CCY_PROC();
        if (pgmRtn) return;
        B030_CRT_UPD_CCY_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCICCYU.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCICCYU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYU.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_M_INPUT, DCCICCYU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYU.CCY_TYPE != '1' 
            && DCCICCYU.CCY_TYPE != '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCICCYU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYU.CCY.equalsIgnoreCase("156") 
            && DCCICCYU.CCY_TYPE == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCICCYU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYU.DRCR_FLG != 'C' 
            && DCCICCYU.DRCR_FLG != 'D') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_DRCR_INVALID, DCCICCYU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!DCCICCYU.APP.equalsIgnoreCase("DD") 
            && !DCCICCYU.APP.equalsIgnoreCase("TD")) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_APP_INVALID, DCCICCYU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DCCICCYU.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCICCYU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_CRT_UPD_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = DCCICCYU.VIA_AC;
        DCRIACCY.KEY.CCY = DCCICCYU.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCICCYU.CCY_TYPE;
        T00_READ_DCTIACCY_R();
        if (pgmRtn) return;
        if (WS_IACCY_REC_FLG == 'Y') {
            R000_UPD_CCY_PROC();
            if (pgmRtn) return;
        } else {
            R000_CRT_CCY_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CRT_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = DCCICCYU.VIA_AC;
        DCRIACCY.KEY.CCY = DCCICCYU.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCICCYU.CCY_TYPE;
        if (DCCICCYU.APP.equalsIgnoreCase("DD")) {
            DCRIACCY.DD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            } else {
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            }
        }
        if (DCCICCYU.APP.equalsIgnoreCase("TD")) {
            DCRIACCY.TD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            } else {
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            }
        }
        DCRIACCY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACCY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTIACCY();
        if (pgmRtn) return;
    }
    public void R000_CRT_CCY_PROC1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = DCCICCYU.VIA_AC;
        DCRIACCY.KEY.CCY = DCCICCYU.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCICCYU.CCY_TYPE;
        if (DCCICCYU.APP.equalsIgnoreCase("DD")) {
            if (SCCGWA.COMM_AREA.AC_DATE != DCRIACCY.LAST_DD_ACDT) {
                if (SCCGWA.COMM_AREA.AC_DATE > DCRIACCY.LAST_DD_ACDT) {
                    DCRIACCY.LAST_DD_BAL = DCRIACCY.DD_TOT_BAL;
                    DCRIACCY.LAST_DD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        if (DCCICCYU.DRCR_FLG == 'D') {
                            DCRIACCY.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL + DCCICCYU.TRS_AMT;
                        }
                        if (DCCICCYU.DRCR_FLG == 'C') {
                            DCRIACCY.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL - DCCICCYU.TRS_AMT;
                        }
                    } else {
                        if (DCCICCYU.DRCR_FLG == 'C') {
                            DCRIACCY.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL + DCCICCYU.TRS_AMT;
                        }
                        if (DCCICCYU.DRCR_FLG == 'D') {
                            DCRIACCY.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL - DCCICCYU.TRS_AMT;
                        }
                    }
                }
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            } else {
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            }
        }
        if (DCCICCYU.APP.equalsIgnoreCase("TD")) {
            if (SCCGWA.COMM_AREA.AC_DATE != DCRIACCY.LAST_TD_ACDT) {
                if (SCCGWA.COMM_AREA.AC_DATE > DCRIACCY.LAST_TD_ACDT) {
                    DCRIACCY.LAST_TD_BAL = DCRIACCY.TD_TOT_BAL;
                    DCRIACCY.LAST_TD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        if (DCCICCYU.DRCR_FLG == 'D') {
                            DCRIACCY.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL + DCCICCYU.TRS_AMT;
                        }
                        if (DCCICCYU.DRCR_FLG == 'C') {
                            DCRIACCY.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL - DCCICCYU.TRS_AMT;
                        }
                    } else {
                        if (DCCICCYU.DRCR_FLG == 'C') {
                            DCRIACCY.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL + DCCICCYU.TRS_AMT;
                        }
                        if (DCCICCYU.DRCR_FLG == 'D') {
                            DCRIACCY.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL - DCCICCYU.TRS_AMT;
                        }
                    }
                }
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            } else {
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            }
        }
        DCRIACCY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACCY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTIACCY();
        if (pgmRtn) return;
    }
    public void R000_UPD_CCY_PROC() throws IOException,SQLException,Exception {
        if (DCCICCYU.APP.equalsIgnoreCase("DD")) {
            if (SCCGWA.COMM_AREA.AC_DATE != DCRIACCY.DD_ACDT) {
                DCRIACCY.LAST_DD_BAL = DCRIACCY.DD_TOT_BAL;
                DCRIACCY.LAST_DD_ACDT = DCRIACCY.DD_ACDT;
                DCRIACCY.DD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            } else {
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            }
        }
        if (DCCICCYU.APP.equalsIgnoreCase("TD")) {
            if (SCCGWA.COMM_AREA.AC_DATE != DCRIACCY.TD_ACDT) {
                DCRIACCY.LAST_TD_BAL = DCRIACCY.TD_TOT_BAL;
                DCRIACCY.LAST_TD_ACDT = DCRIACCY.TD_ACDT;
                DCRIACCY.TD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            } else {
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            }
        }
        DCRIACCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTIACCY();
        if (pgmRtn) return;
    }
    public void R000_UPD_CCY_PROC1() throws IOException,SQLException,Exception {
        if (DCCICCYU.APP.equalsIgnoreCase("DD")) {
            if (SCCGWA.COMM_AREA.AC_DATE != DCRIACCY.LAST_DD_ACDT) {
                if (SCCGWA.COMM_AREA.AC_DATE > DCRIACCY.LAST_DD_ACDT) {
                    DCRIACCY.LAST_DD_BAL = DCRIACCY.DD_TOT_BAL;
                    DCRIACCY.LAST_DD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        if (DCCICCYU.DRCR_FLG == 'D') {
                            DCRIACCY.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL + DCCICCYU.TRS_AMT;
                        }
                        if (DCCICCYU.DRCR_FLG == 'C') {
                            DCRIACCY.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL - DCCICCYU.TRS_AMT;
                        }
                    } else {
                        if (DCCICCYU.DRCR_FLG == 'C') {
                            DCRIACCY.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL + DCCICCYU.TRS_AMT;
                        }
                        if (DCCICCYU.DRCR_FLG == 'D') {
                            DCRIACCY.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL - DCCICCYU.TRS_AMT;
                        }
                    }
                }
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            } else {
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            }
        }
        if (DCCICCYU.APP.equalsIgnoreCase("TD")) {
            if (SCCGWA.COMM_AREA.AC_DATE != DCRIACCY.LAST_TD_ACDT) {
                if (SCCGWA.COMM_AREA.AC_DATE > DCRIACCY.LAST_TD_ACDT) {
                    DCRIACCY.LAST_TD_BAL = DCRIACCY.TD_TOT_BAL;
                    DCRIACCY.LAST_TD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        if (DCCICCYU.DRCR_FLG == 'D') {
                            DCRIACCY.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL + DCCICCYU.TRS_AMT;
                        }
                        if (DCCICCYU.DRCR_FLG == 'C') {
                            DCRIACCY.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL - DCCICCYU.TRS_AMT;
                        }
                    } else {
                        if (DCCICCYU.DRCR_FLG == 'C') {
                            DCRIACCY.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL + DCCICCYU.TRS_AMT;
                        }
                        if (DCCICCYU.DRCR_FLG == 'D') {
                            DCRIACCY.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL - DCCICCYU.TRS_AMT;
                        }
                    }
                }
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            } else {
                if (DCCICCYU.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCICCYU.TRS_AMT;
                }
                if (DCCICCYU.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCICCYU.TRS_AMT;
                }
            }
        }
        DCRIACCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTIACCY();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void T00_READ_DCTIACCY_R() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        DCTIACCY_RD.upd = true;
        DCTIACCY_RD.errhdl = true;
        IBS.READ(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IACCY_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IACCY_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIACCY ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        DCTIACCY_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTIACCY ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        DCTIACCY_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_CCY_RCD_ALR_EXS, DCCICCYU.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
        if (DCCICCYU.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCICCYU=");
            CEP.TRC(SCCGWA, DCCICCYU);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
