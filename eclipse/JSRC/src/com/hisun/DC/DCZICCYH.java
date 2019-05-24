package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZICCYH {
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
    DCCICCYH DCCICCYH;
    public void MP(SCCGWA SCCGWA, DCCICCYH DCCICCYH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCICCYH = DCCICCYH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZICCYH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCICCYH.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_CCY_PROC();
        if (pgmRtn) return;
        B030_UPD_HLD_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCICCYH.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYH.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_M_INPUT, DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYH.CCY_TYPE != '1' 
            && DCCICCYH.CCY_TYPE != '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYH.CCY.equalsIgnoreCase("156") 
            && DCCICCYH.CCY_TYPE == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYH.HLD_MTH != '1' 
            && DCCICCYH.HLD_MTH != '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_HLD_MTH_INVALID, DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYH.HLD_OPR != '1' 
            && DCCICCYH.HLD_OPR != '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_HLD_OPR_INVALID, DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYH.HLD_AMT <= 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_HLD_AMT_INVALID, DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYH.HLD_MTH == '2' 
            && !DCCICCYH.APP.equalsIgnoreCase("DD") 
            && !DCCICCYH.APP.equalsIgnoreCase("TD")) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_APP_INVALID, DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DCCICCYH.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCICCYH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPD_HLD_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = DCCICCYH.VIA_AC;
        DCRIACCY.KEY.CCY = DCCICCYH.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCICCYH.CCY_TYPE;
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
        DCRIACCY.KEY.VIA_AC = DCCICCYH.VIA_AC;
        DCRIACCY.KEY.CCY = DCCICCYH.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCICCYH.CCY_TYPE;
        if (DCCICCYH.HLD_MTH == '1') {
            if (DCCICCYH.HLD_OPR == '1') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL - DCCICCYH.HLD_AMT;
                } else {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL + DCCICCYH.HLD_AMT;
                }
            }
            if (DCCICCYH.HLD_OPR == '2') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL + DCCICCYH.HLD_AMT;
                } else {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL - DCCICCYH.HLD_AMT;
                }
            }
        }
        if (DCCICCYH.HLD_MTH == '2') {
            if (DCCICCYH.APP.equalsIgnoreCase("DD")) {
                if (DCCICCYH.HLD_OPR == '1') {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        DCRIACCY.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL - DCCICCYH.HLD_AMT;
                    } else {
                        DCRIACCY.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL + DCCICCYH.HLD_AMT;
                    }
                }
                if (DCCICCYH.HLD_OPR == '2') {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        DCRIACCY.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL + DCCICCYH.HLD_AMT;
                    } else {
                        DCRIACCY.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL - DCCICCYH.HLD_AMT;
                    }
                }
            }
            if (DCCICCYH.APP.equalsIgnoreCase("TD")) {
                if (DCCICCYH.HLD_OPR == '1') {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        DCRIACCY.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL - DCCICCYH.HLD_AMT;
                    } else {
                        DCRIACCY.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL + DCCICCYH.HLD_AMT;
                    }
                }
                if (DCCICCYH.HLD_OPR == '2') {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        DCRIACCY.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL + DCCICCYH.HLD_AMT;
                    } else {
                        DCRIACCY.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL - DCCICCYH.HLD_AMT;
                    }
                }
            }
            if (DCCICCYH.HLD_OPR == '1') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL - DCCICCYH.HLD_AMT;
                } else {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL + DCCICCYH.HLD_AMT;
                }
            }
            if (DCCICCYH.HLD_OPR == '2') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL + DCCICCYH.HLD_AMT;
                } else {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL - DCCICCYH.HLD_AMT;
                }
            }
        }
        DCRIACCY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACCY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTIACCY();
        if (pgmRtn) return;
    }
    public void R000_UPD_CCY_PROC() throws IOException,SQLException,Exception {
        if (DCCICCYH.HLD_MTH == '1') {
            if (DCCICCYH.HLD_OPR == '1') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL - DCCICCYH.HLD_AMT;
                } else {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL + DCCICCYH.HLD_AMT;
                }
            }
            if (DCCICCYH.HLD_OPR == '2') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL + DCCICCYH.HLD_AMT;
                } else {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL - DCCICCYH.HLD_AMT;
                }
            }
        }
        if (DCCICCYH.HLD_MTH == '2') {
            if (DCCICCYH.APP.equalsIgnoreCase("DD")) {
                if (DCCICCYH.HLD_OPR == '1') {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        DCRIACCY.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL - DCCICCYH.HLD_AMT;
                    } else {
                        DCRIACCY.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL + DCCICCYH.HLD_AMT;
                    }
                }
                if (DCCICCYH.HLD_OPR == '2') {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        DCRIACCY.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL + DCCICCYH.HLD_AMT;
                    } else {
                        DCRIACCY.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL - DCCICCYH.HLD_AMT;
                    }
                }
            }
            if (DCCICCYH.APP.equalsIgnoreCase("TD")) {
                if (DCCICCYH.HLD_OPR == '1') {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        DCRIACCY.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL - DCCICCYH.HLD_AMT;
                    } else {
                        DCRIACCY.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL + DCCICCYH.HLD_AMT;
                    }
                }
                if (DCCICCYH.HLD_OPR == '2') {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        DCRIACCY.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL + DCCICCYH.HLD_AMT;
                    } else {
                        DCRIACCY.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL - DCCICCYH.HLD_AMT;
                    }
                }
            }
            if (DCCICCYH.HLD_OPR == '1') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL - DCCICCYH.HLD_AMT;
                } else {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL + DCCICCYH.HLD_AMT;
                }
            }
            if (DCCICCYH.HLD_OPR == '2') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL + DCCICCYH.HLD_AMT;
                } else {
                    DCRIACCY.TOT_HLD_BAL = DCRIACCY.TOT_HLD_BAL - DCCICCYH.HLD_AMT;
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
        IBS.READ(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IACCY_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IACCY_REC_FLG = 'N';
            Z_RET();
            if (pgmRtn) return;
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
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_CCY_RCD_ALR_EXS, DCCICCYH.RC);
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
        if (DCCICCYH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCICCYH=");
            CEP.TRC(SCCGWA, DCCICCYH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
