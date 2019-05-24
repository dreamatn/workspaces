package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSROZM {
    DBParm DDTCCZM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD157";
    int WS_OUT_CNT = 0;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    DDCOROZM DDCOROZM = new DDCOROZM();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRCCZM DDRCCZM = new DDRCCZM();
    DDRCCZM DDRCCZM1 = new DDRCCZM();
    SCCGWA SCCGWA;
    DDCSROZM DDCSROZM;
    public void MP(SCCGWA SCCGWA, DDCSROZM DDCSROZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSROZM = DDCSROZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSROZM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCSROZM.FUNC == 'Q') {
            B200_INQUIR_CCZM_PROC();
            if (pgmRtn) return;
        } else if (DDCSROZM.FUNC == 'R') {
            B300_REOPEN_CCZM_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCSROZM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSROZM.FUNC);
        CEP.TRC(SCCGWA, DDCSROZM.BV_NO);
        CEP.TRC(SCCGWA, DDCSROZM.REF_NO);
        CEP.TRC(SCCGWA, DDCSROZM.BV_CODE);
        if (DDCSROZM.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDCSROZM.BV_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_BV_NO_M_INPUT;
            if (DDCSROZM.BV_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDCSROZM.BV_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDCSROZM.REF_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_REF_NO_M_INPUT;
            if (DDCSROZM.REF_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDCSROZM.REF_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_MSGID = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_INQUIR_CCZM_PROC() throws IOException,SQLException,Exception {
        B210_GET_INFO_DDTCCZM();
        if (pgmRtn) return;
        B400_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B210_GET_INFO_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCZM);
        DDRCCZM.KEY.OPEN_BV_CODE = "C00008";
        DDRCCZM.KEY.OPEN_BV = DDCSROZM.BV_NO;
        DDRCCZM.KEY.REF_NO = DDCSROZM.REF_NO;
        R000_READ_DDTCCZM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCZM_REC_NOTFND);
        }
    }
    public void R000_READ_DDTCCZM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV_CODE);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.REF_NO);
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        DDTCCZM_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCZM, DDTCCZM_RD);
    }
    public void B300_REOPEN_CCZM_PROC() throws IOException,SQLException,Exception {
        B310_CHECK_DDTCCZM_REC();
        if (pgmRtn) return;
        B320_CCZM_REOPEN_PROC();
        if (pgmRtn) return;
        B400_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B310_CHECK_DDTCCZM_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCZM);
        DDRCCZM.KEY.OPEN_BV = DDCSROZM.BV_NO;
        DDRCCZM.KEY.OPEN_BV_CODE = DDCSROZM.BV_CODE;
        DDRCCZM.KEY.REF_NO = DDCSROZM.REF_NO;
        R000_READUPDATE_DDTCCZM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCZM_REC_NOTFND);
        } else {
            if (DDRCCZM.STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCZM_STS_NOT_NORMAL);
            }
            if (DDRCCZM.BAL_TYPE == '2') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TIME_POINT_CANT_REOP);
            }
        }
    }
    public void B320_CCZM_REOPEN_PROC() throws IOException,SQLException,Exception {
        DDRCCZM.STS = 'R';
        DDRCCZM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCZM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        R000_REWRITE_DDTCCZM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCZM1);
        IBS.CLONE(SCCGWA, DDRCCZM, DDRCCZM1);
        CEP.TRC(SCCGWA, DDCSROZM.NEW_BV_NO);
        DDRCCZM1.KEY.OPEN_BV = DDCSROZM.NEW_BV_NO;
        DDRCCZM1.STS = 'N';
        DDRCCZM1.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM1.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCZM1.BANK_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRCCZM1.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM1.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.CLONE(SCCGWA, DDRCCZM1, DDRCCZM);
        R000_WRITE_DDTCCZM();
        if (pgmRtn) return;
        B200_BV_USE();
        if (pgmRtn) return;
    }
    public void B200_BV_USE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-BV-USE");
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.EC_IND = '1';
        BPCUBUSE.BV_CODE = DDRCCZM.KEY.OPEN_BV_CODE;
        BPCUBUSE.BEG_NO = DDRCCZM.KEY.OPEN_BV;
        BPCUBUSE.END_NO = DDRCCZM.KEY.OPEN_BV;
        BPCUBUSE.NUM = 1;
        BPCUBUSE.COUNT_MTH = '1';
        BPCUBUSE.VB_FLG = '0';
        CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.NUM);
        CEP.TRC(SCCGWA, BPCUBUSE.COUNT_MTH);
        CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B400_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        IBS.init(SCCGWA, DDCOROZM);
        DDCOROZM.FUNC = DDCSROZM.FUNC;
        DDCOROZM.OPEN_BV_CODE = DDRCCZM.KEY.OPEN_BV_CODE;
        DDCOROZM.OPEN_BV = DDRCCZM.KEY.OPEN_BV;
        DDCOROZM.REF_NO = DDRCCZM.KEY.REF_NO;
        DDCOROZM.STS = DDRCCZM.STS;
        DDCOROZM.CI_NO = DDRCCZM.CI_NO;
        DDCOROZM.OPEN_CNT = DDRCCZM.OPEN_CNT;
        DDCOROZM.CH_TLE = DDRCCZM.CH_TLE;
        DDCOROZM.EN_TLE = DDRCCZM.EN_TLE;
        DDCOROZM.EN_NAME = DDRCCZM.EN_NAME;
        DDCOROZM.BAL_TYPE = DDRCCZM.BAL_TYPE;
        DDCOROZM.BAL_DATE = DDRCCZM.BAL_DATE;
        DDCOROZM.BAL_EXPD = DDRCCZM.BAL_EXPD;
        DDCOROZM.BANK_NO = DDRCCZM.BANK_NO;
        DDCOROZM.CRT_DATE = DDRCCZM.CRT_DATE;
        DDCOROZM.CRT_TLR = DDRCCZM.CRT_TLR;
        DDCOROZM.UPDTBL_DATE = DDRCCZM.UPDTBL_DATE;
        DDCOROZM.UPDTBL_TLR = DDRCCZM.UPDTBL_TLR;
        CEP.TRC(SCCGWA, DDCOROZM.FUNC);
        CEP.TRC(SCCGWA, DDCOROZM.OPEN_BV_CODE);
        CEP.TRC(SCCGWA, DDCOROZM.OPEN_BV);
        CEP.TRC(SCCGWA, DDCOROZM.REF_NO);
        CEP.TRC(SCCGWA, DDCOROZM.STS);
        CEP.TRC(SCCGWA, DDCOROZM.CI_NO);
        CEP.TRC(SCCGWA, DDCOROZM.OPEN_CNT);
        CEP.TRC(SCCGWA, DDCOROZM.CH_TLE);
        CEP.TRC(SCCGWA, DDCOROZM.EN_TLE);
        CEP.TRC(SCCGWA, DDCOROZM.EN_NAME);
        CEP.TRC(SCCGWA, DDCOROZM.BAL_TYPE);
        CEP.TRC(SCCGWA, DDCOROZM.BAL_DATE);
        CEP.TRC(SCCGWA, DDCOROZM.BAL_EXPD);
        CEP.TRC(SCCGWA, DDCOROZM.BANK_NO);
        CEP.TRC(SCCGWA, DDCOROZM.CRT_DATE);
        CEP.TRC(SCCGWA, DDCOROZM.CRT_TLR);
        CEP.TRC(SCCGWA, DDCOROZM.UPDTBL_DATE);
        CEP.TRC(SCCGWA, DDCOROZM.UPDTBL_TLR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOROZM;
        SCCFMT.DATA_LEN = 880;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE, true);
    }
    public void R000_READUPDATE_DDTCCZM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV_CODE);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.REF_NO);
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        DDTCCZM_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCZM, DDTCCZM_RD);
    }
    public void R000_REWRITE_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        IBS.REWRITE(SCCGWA, DDRCCZM, DDTCCZM_RD);
    }
    public void R000_WRITE_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        IBS.WRITE(SCCGWA, DDRCCZM, DDTCCZM_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
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
