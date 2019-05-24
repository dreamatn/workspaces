package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUATMS {
    DBParm DCTATMS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_PARM_CODE = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRATMS DCRATMS = new DCRATMS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUATMS DCCUATMS;
    public void MP(SCCGWA SCCGWA, DCCUATMS DCCUATMS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUATMS = DCCUATMS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUATMS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCUATMS.OUTPUT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUATMS.FUNC);
        CEP.TRC(SCCGWA, DCCUATMS.CARD_NO);
        CEP.TRC(SCCGWA, DCCUATMS.TXN_TYPE);
        CEP.TRC(SCCGWA, DCCUATMS.MONTH);
        CEP.TRC(SCCGWA, DCCUATMS.TXN_AMT);
        CEP.TRC(SCCGWA, DCCUATMS.REGN_TYPE);
        CEP.TRC(SCCGWA, DCCUATMS.TC_FLG);
        if (DCCUATMS.FUNC == 'Q') {
            B010_QUERY_PROC();
            if (pgmRtn) return;
        } else if (DCCUATMS.FUNC == 'A') {
            B020_DATA_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.INPUT_FALSE);
        }
    }
    public void B010_QUERY_PROC() throws IOException,SQLException,Exception {
        if ((DCCUATMS.CARD_NO.trim().length() == 0) 
            || (DCCUATMS.TXN_TYPE.trim().length() == 0) 
            || (DCCUATMS.REGN_TYPE.trim().length() == 0) 
            || (DCCUATMS.TC_FLG.trim().length() == 0)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT);
        }
        IBS.init(SCCGWA, DCRATMS);
        DCRATMS.KEY.CARD_NO = DCCUATMS.CARD_NO;
        DCRATMS.KEY.TXN_TYPE = DCCUATMS.TXN_TYPE;
        DCRATMS.KEY.REGN_TYPE = DCCUATMS.REGN_TYPE;
        DCRATMS.KEY.TC_FLG = DCCUATMS.TC_FLG;
        T000_READ_DCTATMS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DCCUATMS.OUTPUT.JAN_CNT = DCRATMS.JAN_CNT;
            DCCUATMS.OUTPUT.FEB_CNT = DCRATMS.FEB_CNT;
            DCCUATMS.OUTPUT.MAR_CNT = DCRATMS.MAR_CNT;
            DCCUATMS.OUTPUT.APR_CNT = DCRATMS.APR_CNT;
            DCCUATMS.OUTPUT.MAY_CNT = DCRATMS.MAY_CNT;
            DCCUATMS.OUTPUT.JUN_CNT = DCRATMS.JUN_CNT;
            DCCUATMS.OUTPUT.JUL_CNT = DCRATMS.JUL_CNT;
            DCCUATMS.OUTPUT.AUG_CNT = DCRATMS.AUG_CNT;
            DCCUATMS.OUTPUT.SEP_CNT = DCRATMS.SEP_CNT;
            DCCUATMS.OUTPUT.OCT_CNT = DCRATMS.OCT_CNT;
            DCCUATMS.OUTPUT.NOV_CNT = DCRATMS.NOV_CNT;
            DCCUATMS.OUTPUT.DEC_CNT = DCRATMS.DEC_CNT;
            DCCUATMS.OUTPUT.JAN_AMT = DCRATMS.JAN_AMT;
            DCCUATMS.OUTPUT.FEB_AMT = DCRATMS.FEB_AMT;
            DCCUATMS.OUTPUT.MAR_AMT = DCRATMS.MAR_AMT;
            DCCUATMS.OUTPUT.APR_AMT = DCRATMS.APR_AMT;
            DCCUATMS.OUTPUT.MAY_AMT = DCRATMS.MAY_AMT;
            DCCUATMS.OUTPUT.JUN_AMT = DCRATMS.JUN_AMT;
            DCCUATMS.OUTPUT.JUL_AMT = DCRATMS.JUL_AMT;
            DCCUATMS.OUTPUT.AUG_AMT = DCRATMS.AUG_AMT;
            DCCUATMS.OUTPUT.SEP_AMT = DCRATMS.SEP_AMT;
            DCCUATMS.OUTPUT.OCT_AMT = DCRATMS.OCT_AMT;
            DCCUATMS.OUTPUT.NOV_AMT = DCRATMS.NOV_AMT;
            DCCUATMS.OUTPUT.DEC_AMT = DCRATMS.DEC_AMT;
            if (DCCUATMS.MONTH.trim().length() > 0) {
                if (DCCUATMS.MONTH.equalsIgnoreCase("01")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.JAN_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.JAN_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("02")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.FEB_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.FEB_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("03")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.MAR_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.MAR_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("04")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.APR_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.APR_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("05")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.MAY_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.MAY_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("06")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.JUN_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.JUN_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("07")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.JUL_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.JUL_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("08")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.AUG_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.AUG_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("09")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.SEP_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.SEP_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("10")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.OCT_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.OCT_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("11")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.NOV_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.NOV_AMT;
                } else if (DCCUATMS.MONTH.equalsIgnoreCase("12")) {
                    DCCUATMS.OUTPUT.O_CNT = DCRATMS.DEC_CNT;
                    DCCUATMS.OUTPUT.O_AMT = DCRATMS.DEC_AMT;
                } else {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ATMS_TXN_MONTH_ERR);
                }
            }
        }
    }
    public void B030_QUERY_Z_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRATMS);
        DCRATMS.KEY.CARD_NO = DCCUATMS.CARD_NO;
        DCRATMS.KEY.REGN_TYPE = DCCUATMS.REGN_TYPE;
        DCRATMS.KEY.TC_FLG = DCCUATMS.TC_FLG;
        DCRATMS.KEY.TXN_TYPE = "01";
        B030_READ_TABLE();
        if (pgmRtn) return;
        DCRATMS.KEY.TXN_TYPE = "02";
        B030_READ_TABLE();
        if (pgmRtn) return;
        DCRATMS.KEY.TXN_TYPE = "03";
        B030_READ_TABLE();
        if (pgmRtn) return;
    }
    public void B020_DATA_PROC() throws IOException,SQLException,Exception {
        if ((DCCUATMS.CARD_NO.trim().length() == 0) 
            || (DCCUATMS.TXN_TYPE.trim().length() == 0) 
            || (DCCUATMS.TXN_AMT == 0) 
            || (DCCUATMS.REGN_TYPE.trim().length() == 0) 
            || (DCCUATMS.TC_FLG.trim().length() == 0) 
            || (DCCUATMS.MONTH.trim().length() == 0)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT);
        }
        IBS.init(SCCGWA, DCRATMS);
        DCRATMS.KEY.CARD_NO = DCCUATMS.CARD_NO;
        DCRATMS.KEY.TXN_TYPE = DCCUATMS.TXN_TYPE;
        DCRATMS.KEY.REGN_TYPE = DCCUATMS.REGN_TYPE;
        DCRATMS.KEY.TC_FLG = DCCUATMS.TC_FLG;
        T000_READ_UPD_DCTATMS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            B099_01_MONTH_PROC();
            if (pgmRtn) return;
            DCRATMS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRATMS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRATMS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRATMS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTATMS();
            if (pgmRtn) return;
        } else {
            B099_02_MONTH_PROC();
            if (pgmRtn) return;
            DCRATMS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRATMS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTATMS();
            if (pgmRtn) return;
        }
        B030_QUERY_Z_PROC();
        if (pgmRtn) return;
    }
    public void B030_READ_TABLE() throws IOException,SQLException,Exception {
        T000_READ_DCTATMS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DCCUATMS.OUTPUT.JAN_CNT += DCRATMS.JAN_CNT;
            DCCUATMS.OUTPUT.FEB_CNT += DCRATMS.FEB_CNT;
            DCCUATMS.OUTPUT.MAR_CNT += DCRATMS.MAR_CNT;
            DCCUATMS.OUTPUT.APR_CNT += DCRATMS.APR_CNT;
            DCCUATMS.OUTPUT.MAY_CNT += DCRATMS.MAY_CNT;
            DCCUATMS.OUTPUT.JUN_CNT += DCRATMS.JUN_CNT;
            DCCUATMS.OUTPUT.JUL_CNT += DCRATMS.JUL_CNT;
            DCCUATMS.OUTPUT.AUG_CNT += DCRATMS.AUG_CNT;
            DCCUATMS.OUTPUT.SEP_CNT += DCRATMS.SEP_CNT;
            DCCUATMS.OUTPUT.OCT_CNT += DCRATMS.OCT_CNT;
            DCCUATMS.OUTPUT.NOV_CNT += DCRATMS.NOV_CNT;
            DCCUATMS.OUTPUT.DEC_CNT += DCRATMS.DEC_CNT;
            DCCUATMS.OUTPUT.JAN_AMT += DCRATMS.JAN_AMT;
            DCCUATMS.OUTPUT.FEB_AMT += DCRATMS.FEB_AMT;
            DCCUATMS.OUTPUT.MAR_AMT += DCRATMS.MAR_AMT;
            DCCUATMS.OUTPUT.APR_AMT += DCRATMS.APR_AMT;
            DCCUATMS.OUTPUT.MAY_AMT += DCRATMS.MAY_AMT;
            DCCUATMS.OUTPUT.JUN_AMT += DCRATMS.JUN_AMT;
            DCCUATMS.OUTPUT.JUL_AMT += DCRATMS.JUL_AMT;
            DCCUATMS.OUTPUT.AUG_AMT += DCRATMS.AUG_AMT;
            DCCUATMS.OUTPUT.SEP_AMT += DCRATMS.SEP_AMT;
            DCCUATMS.OUTPUT.OCT_AMT += DCRATMS.OCT_AMT;
            DCCUATMS.OUTPUT.NOV_AMT += DCRATMS.NOV_AMT;
            DCCUATMS.OUTPUT.DEC_AMT += DCRATMS.DEC_AMT;
        }
    }
    public void B099_01_MONTH_PROC() throws IOException,SQLException,Exception {
        if (DCCUATMS.MONTH.equalsIgnoreCase("01")) {
            DCRATMS.JAN_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.JAN_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("02")) {
            DCRATMS.FEB_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.FEB_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("03")) {
            DCRATMS.MAR_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.MAR_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("04")) {
            DCRATMS.APR_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.APR_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("05")) {
            DCRATMS.MAY_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.MAY_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("06")) {
            DCRATMS.JUN_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.JUN_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("07")) {
            DCRATMS.JUL_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.JUL_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("08")) {
            DCRATMS.AUG_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.AUG_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("09")) {
            DCRATMS.SEP_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.SEP_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("10")) {
            DCRATMS.OCT_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.OCT_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("11")) {
            DCRATMS.NOV_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.NOV_CNT = 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("12")) {
            DCRATMS.DEC_AMT = DCCUATMS.TXN_AMT;
            DCRATMS.DEC_CNT = 1;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ATMS_TXN_MONTH_ERR);
        }
    }
    public void B099_02_MONTH_PROC() throws IOException,SQLException,Exception {
        if (DCCUATMS.MONTH.equalsIgnoreCase("01")) {
            DCRATMS.JAN_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.JAN_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("02")) {
            DCRATMS.FEB_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.FEB_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("03")) {
            DCRATMS.MAR_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.MAR_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("04")) {
            DCRATMS.APR_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.APR_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("05")) {
            DCRATMS.MAY_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.MAY_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("06")) {
            DCRATMS.JUN_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.JUN_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("07")) {
            DCRATMS.JUL_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.JUL_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("08")) {
            DCRATMS.AUG_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.AUG_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("09")) {
            DCRATMS.SEP_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.SEP_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("10")) {
            DCRATMS.OCT_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.OCT_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("11")) {
            DCRATMS.NOV_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.NOV_CNT += 1;
        } else if (DCCUATMS.MONTH.equalsIgnoreCase("12")) {
            DCRATMS.DEC_AMT += DCCUATMS.TXN_AMT;
            DCRATMS.DEC_CNT += 1;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ATMS_TXN_MONTH_ERR);
        }
    }
    public void T000_READ_DCTATMS() throws IOException,SQLException,Exception {
        DCTATMS_RD = new DBParm();
        DCTATMS_RD.TableName = "DCTATMS";
        IBS.READ(SCCGWA, DCRATMS, DCTATMS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTATMS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_DCTATMS() throws IOException,SQLException,Exception {
        DCTATMS_RD = new DBParm();
        DCTATMS_RD.TableName = "DCTATMS";
        DCTATMS_RD.upd = true;
        IBS.READ(SCCGWA, DCRATMS, DCTATMS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTATMS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTATMS() throws IOException,SQLException,Exception {
        DCTATMS_RD = new DBParm();
        DCTATMS_RD.TableName = "DCTATMS";
        IBS.WRITE(SCCGWA, DCRATMS, DCTATMS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTATMS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTATMS() throws IOException,SQLException,Exception {
        DCTATMS_RD = new DBParm();
        DCTATMS_RD.TableName = "DCTATMS";
        IBS.REWRITE(SCCGWA, DCRATMS, DCTATMS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTATMS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
