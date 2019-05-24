package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUVCH {
    DBParm DDTVCH_RD;
    brParm DDTVCH_BR = new brParm();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    short WS_R = 0;
    String WS_ACO_NO = " ";
    String WS_DDAC_STSW = " ";
    String WS_CUS_AC = " ";
    char WS_DDTVCH_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRVCH DDRVCH = new DDRVCH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCUVCH DDCUVCH;
    public void MP(SCCGWA SCCGWA, DDCUVCH DDCUVCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUVCH = DDCUVCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUVCH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DDRVCH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK_PROC();
        B200_INQ_MAIN_PROC();
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUVCH.KEY.CUS_AC);
        CEP.TRC(SCCGWA, DDCUVCH.KEY.VCH_TYPE);
        if (DDCUVCH.KEY.CUS_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CUS_AC_INPUT_ERR);
        }
        if (DDCUVCH.FUNC == '1' 
            && (DDCUVCH.KEY.VCH_TYPE == ' ' 
            || DDCUVCH.KEY.VCH_TYPE == 0X00)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VCH_TYPE_INPUT_ERR);
        }
    }
    public void B200_INQ_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCUVCH.FUNC == '1') {
            B300_INQ_CUS_VCH_BY_AC_VCH_TYP();
        } else if (DDCUVCH.FUNC == '2') {
            B400_INQ_CUS_VCH_BY_AC();
        } else {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_TYPE_ERR);
        }
    }
    public void B300_INQ_CUS_VCH_BY_AC_VCH_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCUVCH.KEY.CUS_AC;
        DDRVCH.VCH_TYPE = DDCUVCH.KEY.VCH_TYPE;
        T000_READ_DDTVCH();
        if (DDCUVCH.EXIST_FLG == 'Y') {
            DDCUVCH.OWNER_BK = DDRVCH.OWNER_BK;
            DDCUVCH.PAY_TYPE = DDRVCH.PAY_TYPE;
            DDCUVCH.PSWD_FLG = DDRVCH.PSWD_FLG;
            DDCUVCH.PAY_PSWD = DDRVCH.PAY_PSWD;
            DDCUVCH.PSWD_ERR_NUM = DDRVCH.PSWD_ERR_NUM;
            DDCUVCH.PSWD_ERR_DATE = DDRVCH.PSWD_ERR_DATE;
            DDCUVCH.PAY_IDTYPE = DDRVCH.PAY_IDTYPE;
            DDCUVCH.PAY_IDNO = DDRVCH.PAY_IDNO;
            DDCUVCH.PAY_SIGN_NO = DDRVCH.PAY_SIGN_NO;
            DDCUVCH.PSBK_NO = DDRVCH.PSBK_NO;
            DDCUVCH.PSBK_STS = DDRVCH.PSBK_STS;
            DDCUVCH.PSBK_SEQ = DDRVCH.PSBK_SEQ;
            DDCUVCH.PRT_LINE = DDRVCH.PRT_LINE;
            DDCUVCH.UPT_CNT = DDRVCH.UPT_CNT;
            DDCUVCH.UPT_STR_NO = DDRVCH.UPT_STR_NO;
            DDCUVCH.UPT_LAST_NO = DDRVCH.UPT_LAST_NO;
            DDCUVCH.LAST_PB_CCY = DDRVCH.LAST_PB_CCY;
            DDCUVCH.LAST_PB_BAL = DDRVCH.LAST_PB_BAL;
            DDCUVCH.W_LOST_DATE = DDRVCH.W_LOST_DATE;
            DDCUVCH.LOST_EXP_DATE = DDRVCH.LOST_EXP_DATE;
            DDCUVCH.PWD_LOST_DATE = DDRVCH.PWD_LOST_DATE;
            DDCUVCH.LOST_NO = DDRVCH.LOST_NO;
            DDCUVCH.PWD_LOST_NO = DDRVCH.PWD_LOST_NO;
            DDCUVCH.CRT_DATE = DDRVCH.CRT_DATE;
            DDCUVCH.CRT_TLR = DDRVCH.CRT_TLR;
            DDCUVCH.UPDTBL_DATE = DDRVCH.UPDTBL_DATE;
            DDCUVCH.UPDTBL_TLR = DDRVCH.UPDTBL_TLR;
        }
    }
    public void B400_INQ_CUS_VCH_BY_AC() throws IOException,SQLException,Exception {
        WS_CNT = 0;
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCUVCH.KEY.CUS_AC;
        T000_STARTBR_DDTVCH();
        T000_READNEXT_DDTVCH();
        if (WS_DDTVCH_FLG == 'Y') {
            DDCUVCH.EXIST_FLG = 'Y';
            DDCUVCH.KEY.VCH_TYPE = DDRVCH.VCH_TYPE;
            DDCUVCH.OWNER_BK = DDRVCH.OWNER_BK;
            DDCUVCH.PAY_TYPE = DDRVCH.PAY_TYPE;
            DDCUVCH.PSWD_FLG = DDRVCH.PSWD_FLG;
            DDCUVCH.PAY_PSWD = DDRVCH.PAY_PSWD;
            DDCUVCH.PSWD_ERR_NUM = DDRVCH.PSWD_ERR_NUM;
            DDCUVCH.PSWD_ERR_DATE = DDRVCH.PSWD_ERR_DATE;
            DDCUVCH.PAY_IDTYPE = DDRVCH.PAY_IDTYPE;
            DDCUVCH.PAY_IDNO = DDRVCH.PAY_IDNO;
            DDCUVCH.PAY_SIGN_NO = DDRVCH.PAY_SIGN_NO;
            DDCUVCH.PSBK_NO = DDRVCH.PSBK_NO;
            DDCUVCH.PSBK_STS = DDRVCH.PSBK_STS;
            DDCUVCH.PSBK_SEQ = DDRVCH.PSBK_SEQ;
            DDCUVCH.PRT_LINE = DDRVCH.PRT_LINE;
            DDCUVCH.UPT_CNT = DDRVCH.UPT_CNT;
            DDCUVCH.UPT_STR_NO = DDRVCH.UPT_STR_NO;
            DDCUVCH.UPT_LAST_NO = DDRVCH.UPT_LAST_NO;
            DDCUVCH.LAST_PB_CCY = DDRVCH.LAST_PB_CCY;
            DDCUVCH.LAST_PB_BAL = DDRVCH.LAST_PB_BAL;
            DDCUVCH.W_LOST_DATE = DDRVCH.W_LOST_DATE;
            DDCUVCH.LOST_EXP_DATE = DDRVCH.LOST_EXP_DATE;
            DDCUVCH.PWD_LOST_DATE = DDRVCH.PWD_LOST_DATE;
            DDCUVCH.LOST_NO = DDRVCH.LOST_NO;
            DDCUVCH.PWD_LOST_NO = DDRVCH.PWD_LOST_NO;
            DDCUVCH.CRT_DATE = DDRVCH.CRT_DATE;
            DDCUVCH.CRT_TLR = DDRVCH.CRT_TLR;
            DDCUVCH.UPDTBL_DATE = DDRVCH.UPDTBL_DATE;
            DDCUVCH.UPDTBL_TLR = DDRVCH.UPDTBL_TLR;
        } else {
            DDCUVCH.EXIST_FLG = 'N';
        }
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DDCUVCH.EXIST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            DDCUVCH.EXIST_FLG = 'N';
        } else {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VCH_NFD_ERR);
        }
    }
    public void T000_STARTBR_DDTVCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVCH.KEY.CUS_AC);
        DDTVCH_BR.rp = new DBParm();
        DDTVCH_BR.rp.TableName = "DDTVCH";
        DDTVCH_BR.rp.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.STARTBR(SCCGWA, DDRVCH, this, DDTVCH_BR);
    }
    public void T000_READNEXT_DDTVCH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRVCH, this, DDTVCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTVCH_FLG = 'Y';
        } else {
            WS_DDTVCH_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTVCH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTVCH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
