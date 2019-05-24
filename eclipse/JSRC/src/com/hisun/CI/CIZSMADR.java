package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSMADR {
    DBParm CITRISK_RD;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    brParm CITADR_BR = new brParm();
    DBParm CITADR_RD;
    DBParm DUPKEY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    char WS_RESIDENT = ' ';
    String WS_MSG_INFO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRADR CIRADR = new CIRADR();
    CIRADR CIRADRN = new CIRADR();
    CIRADR CIRADRO = new CIRADR();
    CIRRISK CIRRISK = new CIRRISK();
    CIRPDM CIRPDM = new CIRPDM();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CICOADR CICOADR = new CICOADR();
    CICCGHIS CICCGHIS = new CICCGHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMADR CICSMADR;
    public void MP(SCCGWA SCCGWA, CICSMADR CICSMADR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMADR = CICSMADR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMADR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMADR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSMADR.FUNC);
        if (CICSMADR.FUNC == 'B') {
            B020_BRW_ADR_INF();
            if (pgmRtn) return;
        } else if (CICSMADR.FUNC == 'A') {
            B030_ADD_ADR_INF();
            if (pgmRtn) return;
        } else if (CICSMADR.FUNC == 'M') {
            B040_MOD_ADR_INF();
            if (pgmRtn) return;
        } else if (CICSMADR.FUNC == 'D') {
            B050_DEL_ADR_INF();
            if (pgmRtn) return;
        } else if (CICSMADR.FUNC == 'I') {
            B060_INQ_ADR_INF();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMADR.DATA.CI_NO);
        if (CICSMADR.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSMADR.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP == '3' 
            && (CICSMADR.FUNC == 'A' 
            || CICSMADR.FUNC == 'M' 
            || CICSMADR.FUNC == 'D')) {
            CEP.TRC(SCCGWA, CIRBAS.OPN_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.HQT_BANK);
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != CIRBAS.OPN_BR 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != SCCGWA.COMM_AREA.HQT_BANK) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FIN_CUST_CANT_MOD);
            }
        }
        if (CIRBAS.CI_TYP == '1') {
            IBS.init(SCCGWA, CIRPDM);
            CIRPDM.KEY.CI_NO = CICSMADR.DATA.CI_NO;
            T000_READ_CITPDM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_RESIDENT = CIRPDM.RESIDENT;
            }
        }
        if (CIRBAS.CI_TYP == '2') {
            IBS.init(SCCGWA, CIRCDM);
            CIRCDM.KEY.CI_NO = CICSMADR.DATA.CI_NO;
            T000_READ_CITCDM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_RESIDENT = CIRCDM.RESIDENT;
            }
        }
        if (CIRBAS.CI_TYP == '3') {
            IBS.init(SCCGWA, CIRFDM);
            CIRFDM.KEY.CI_NO = CICSMADR.DATA.CI_NO;
            T000_READ_CITFDM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_RESIDENT = CIRFDM.RESIDENT;
            }
        }
    }
    public void B020_BRW_ADR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRADR);
        CIRADR.KEY.CI_NO = CICSMADR.DATA.CI_NO;
        T000_STARTBR_CITADR();
        if (pgmRtn) return;
        T000_READNEXT_CITADR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ADR INF NOT FND");
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CICOADR);
            CEP.TRC(SCCGWA, CIRADR.ADR_NM);
            CEP.TRC(SCCGWA, CIRADR.POST_CD);
            CICOADR.DATA.ADR_TYPE = CIRADR.KEY.ADR_TYPE;
            CICOADR.DATA.ADR_CNTY = CIRADR.CNTY_CD;
            CICOADR.DATA.ADR_L1 = CIRADR.LADDR_L1;
            CICOADR.DATA.ADR_L2 = CIRADR.LADDR_L2;
            CICOADR.DATA.ADR_L3 = CIRADR.LADDR_L3;
            CICOADR.DATA.ADR_L4 = CIRADR.LADDR_L4;
            CICOADR.DATA.ADR_L5 = CIRADR.LADDR_L5;
            CICOADR.DATA.ADR_L6 = CIRADR.LADDR_L6;
            CICOADR.DATA.ADR_L7 = CIRADR.LADDR_L7;
            CICOADR.DATA.ADR_E2 = CIRADR.LADDR_E2;
            CICOADR.DATA.ADR_NM = CIRADR.ADR_NM;
            CICOADR.DATA.ADR_POST = CIRADR.POST_CD;
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITADR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITADR();
        if (pgmRtn) return;
    }
    public void B030_ADD_ADR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRADR);
        IBS.init(SCCGWA, CIRADRN);
        IBS.init(SCCGWA, CIRADRO);
        CIRADR.KEY.CI_NO = CICSMADR.DATA.CI_NO;
        CIRADR.KEY.ADR_TYPE = CICSMADR.DATA.ADR_TYPE;
        T000_READ_CITADR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ADR_INF_EXIST, CICSMADR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CRS REQ MOD CHECK START");
        CEP.TRC(SCCGWA, WS_RESIDENT);
        if (WS_RESIDENT != '1') {
            B042_MOD_CHECK_INFO();
            if (pgmRtn) return;
        }
        CIRADR.CNTY_CD = CICSMADR.DATA.ADR_CNTY;
        CIRADR.LADDR_L1 = CICSMADR.DATA.ADR_L1;
        CIRADR.LADDR_L2 = CICSMADR.DATA.ADR_L2;
        CIRADR.LADDR_L3 = CICSMADR.DATA.ADR_L3;
        CIRADR.LADDR_L4 = CICSMADR.DATA.ADR_L4;
        CIRADR.LADDR_L5 = CICSMADR.DATA.ADR_L5;
        CIRADR.LADDR_L6 = CICSMADR.DATA.ADR_L6;
        CIRADR.LADDR_L7 = CICSMADR.DATA.ADR_L7;
        CIRADR.LADDR_E2 = CICSMADR.DATA.ADR_E2;
        CIRADR.ADR_NM = CICSMADR.DATA.ADR_NM;
        CIRADR.POST_CD = CICSMADR.DATA.ADR_POST;
        CIRADR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRADR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRADR.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRADR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRADR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRADR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITADR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRADR, CIRADRN);
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITADR";
        CICCGHIS.DATA.OLD_DATA_LEN = 682;
        CICCGHIS.DATA.NEW_DATA_LEN = 682;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRADRO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRADRN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
    }
    public void B040_MOD_ADR_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMADR.DATA.ADR_NM);
        if (CICSMADR.DATA.ADR_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, " ADR NM MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "详细地址必输");
        }
        IBS.init(SCCGWA, CIRADR);
        IBS.init(SCCGWA, CIRADRN);
        IBS.init(SCCGWA, CIRADRO);
        CIRADR.KEY.CI_NO = CICSMADR.DATA.CI_NO;
        CIRADR.KEY.ADR_TYPE = CICSMADR.DATA.ADR_TYPE;
        T000_READ_CITADR_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRADR, CIRADRO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ADR_NOTFND, CICSMADR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "FATCA CHECK START");
        B041_FATCA_CHK_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CRS REQ MOD CHECK START");
        CEP.TRC(SCCGWA, WS_RESIDENT);
        if (WS_RESIDENT != '1') {
            B042_MOD_CHECK_INFO();
            if (pgmRtn) return;
        }
        CIRADR.CNTY_CD = CICSMADR.DATA.ADR_CNTY;
        CIRADR.LADDR_L1 = CICSMADR.DATA.ADR_L1;
        CIRADR.LADDR_L2 = CICSMADR.DATA.ADR_L2;
        CIRADR.LADDR_L3 = CICSMADR.DATA.ADR_L3;
        CIRADR.LADDR_L4 = CICSMADR.DATA.ADR_L4;
        CIRADR.LADDR_L5 = CICSMADR.DATA.ADR_L5;
        CIRADR.LADDR_L6 = CICSMADR.DATA.ADR_L6;
        CIRADR.LADDR_L7 = CICSMADR.DATA.ADR_L7;
        CIRADR.LADDR_E2 = CICSMADR.DATA.ADR_E2;
        CIRADR.ADR_NM = CICSMADR.DATA.ADR_NM;
        CIRADR.POST_CD = CICSMADR.DATA.ADR_POST;
        CIRADR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRADR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRADR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITADR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRADR, CIRADRN);
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITADR";
        CICCGHIS.DATA.OLD_DATA_LEN = 682;
        CICCGHIS.DATA.NEW_DATA_LEN = 682;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRADRO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRADRN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
    }
    public void B041_FATCA_CHK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = CICSMADR.DATA.CI_NO;
        T000_READ_CITRISK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        CEP.TRC(SCCGWA, CIRADR.KEY.ADR_TYPE);
        CEP.TRC(SCCGWA, CIRADR.CNTY_CD);
        CEP.TRC(SCCGWA, CICSMADR.DATA.ADR_TYPE);
        CEP.TRC(SCCGWA, CICSMADR.DATA.ADR_CNTY);
        CEP.TRC(SCCGWA, CIRRISK.FATCA_TP);
        if (CIRBAS.CI_TYP == '1') {
            if (((CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("111") 
                || CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("114")) 
                && !CIRADR.CNTY_CD.equalsIgnoreCase("USA") 
                && (CICSMADR.DATA.ADR_TYPE.equalsIgnoreCase("111") 
                || CICSMADR.DATA.ADR_TYPE.equalsIgnoreCase("114")) 
                && CICSMADR.DATA.ADR_CNTY.equalsIgnoreCase("USA") 
                && CIRRISK.FATCA_TP.equalsIgnoreCase("W800")) 
                || ((CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("111") 
                || CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("114")) 
                && CIRADR.CNTY_CD.equalsIgnoreCase("USA") 
                && (CICSMADR.DATA.ADR_TYPE.equalsIgnoreCase("111") 
                || CICSMADR.DATA.ADR_TYPE.equalsIgnoreCase("114")) 
                && !CICSMADR.DATA.ADR_CNTY.equalsIgnoreCase("USA") 
                && CIRRISK.FATCA_TP.equalsIgnoreCase("W900"))) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MOD_FATCA_TP);
            }
        }
        if (CIRBAS.CI_TYP == '2' 
            || CIRBAS.CI_TYP == '3') {
            if ((CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("210") 
                && !CIRADR.CNTY_CD.equalsIgnoreCase("USA") 
                && CICSMADR.DATA.ADR_TYPE.equalsIgnoreCase("210") 
                && CICSMADR.DATA.ADR_CNTY.equalsIgnoreCase("USA") 
                && CIRRISK.FATCA_TP.equalsIgnoreCase("W800")) 
                || (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("210") 
                && CIRADR.CNTY_CD.equalsIgnoreCase("USA") 
                && CICSMADR.DATA.ADR_TYPE.equalsIgnoreCase("210") 
                && !CICSMADR.DATA.ADR_CNTY.equalsIgnoreCase("USA") 
                && CIRRISK.FATCA_TP.equalsIgnoreCase("W900"))) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MOD_FATCA_TP);
            }
        }
    }
    public void B042_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        if (CICSMADR.DATA.ADR_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "地址类型必须输入");
        }
        if (CICSMADR.DATA.ADR_CNTY.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "国家代码必须输入");
        }
        if ((CICSMADR.DATA.ADR_L1.trim().length() > 0 
            && CICSMADR.DATA.ADR_L2.trim().length() > 0 
            && CICSMADR.DATA.ADR_E2.trim().length() == 0) 
            || (CICSMADR.DATA.ADR_L1.trim().length() == 0 
            && CICSMADR.DATA.ADR_L2.trim().length() == 0 
            && CICSMADR.DATA.ADR_E2.trim().length() > 0)) {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BHA_PAC_ONEIN);
        }
        if (CICSMADR.DATA.ADR_L3.trim().length() == 0 
            && CICSMADR.DATA.ADR_L4.trim().length() == 0 
            && CICSMADR.DATA.ADR_L5.trim().length() == 0 
            && CICSMADR.DATA.ADR_L6.trim().length() == 0 
            && CICSMADR.DATA.ADR_L7.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CLFD_CNT_SPACE);
        }
    }
    public void B050_DEL_ADR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRADR);
        IBS.init(SCCGWA, CIRADRN);
        IBS.init(SCCGWA, CIRADRO);
        CIRADR.KEY.CI_NO = CICSMADR.DATA.CI_NO;
        CIRADR.KEY.ADR_TYPE = CICSMADR.DATA.ADR_TYPE;
        T000_READ_CITADR_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRADR, CIRADRO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ADR_NOTFND, CICSMADR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_DELETE_CITADR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITADR";
        CICCGHIS.DATA.OLD_DATA_LEN = 682;
        CICCGHIS.DATA.NEW_DATA_LEN = 682;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRADRO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRADRN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
    }
    public void B060_INQ_ADR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRADR);
        CIRADR.KEY.CI_NO = CICSMADR.DATA.CI_NO;
        CIRADR.KEY.ADR_TYPE = CICSMADR.DATA.ADR_TYPE;
        T000_READ_CITADR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICSMADR.RLT_FLG = 'Y';
            CICSMADR.DATA.ADR_CNTY = CIRADR.CNTY_CD;
            CICSMADR.DATA.ADR_L1 = CIRADR.LADDR_L1;
            CICSMADR.DATA.ADR_L2 = CIRADR.LADDR_L2;
            CICSMADR.DATA.ADR_L3 = CIRADR.LADDR_L3;
            CICSMADR.DATA.ADR_L4 = CIRADR.LADDR_L4;
            CICSMADR.DATA.ADR_L5 = CIRADR.LADDR_L5;
            CICSMADR.DATA.ADR_L6 = CIRADR.LADDR_L6;
            CICSMADR.DATA.ADR_L7 = CIRADR.LADDR_L7;
            CICSMADR.DATA.ADR_E2 = CIRADR.LADDR_E2;
            CICSMADR.DATA.ADR_NM = CIRADR.ADR_NM;
            CICSMADR.DATA.ADR_POST = CIRADR.POST_CD;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ADR INQUIRE NOT FIND");
            CICSMADR.RLT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITADR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOADR);
        SCCMPAG.DATA_LEN = 600;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCGHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MOD-HISTORY", CICCGHIS);
        if (CICCGHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCGHIS.RC);
        }
    }
    public void T000_READ_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.READ(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_STARTBR_CITADR() throws IOException,SQLException,Exception {
        CITADR_BR.rp = new DBParm();
        CITADR_BR.rp.TableName = "CITADR";
        CITADR_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRADR, CITADR_BR);
    }
    public void T000_READNEXT_CITADR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRADR, this, CITADR_BR);
    }
    public void T000_ENDBR_CITADR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITADR_BR);
    }
    public void T000_READ_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        IBS.READ(SCCGWA, CIRADR, CITADR_RD);
    }
    public void T000_READ_CITADR_UPD() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        CITADR_RD.upd = true;
        IBS.READ(SCCGWA, CIRADR, CITADR_RD);
    }
    public void T000_WRITE_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        IBS.WRITE(SCCGWA, CIRADR, CITADR_RD);
    }
    public void T000_REWRITE_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        CITADR_RD.errhdl = true;
        DUPKEY_RD = new DBParm();
        DUPKEY_RD.TableName = "DUPKEY";
        IBS.REWRITE(SCCGWA, DURKEY, DUPKEY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "地址类型录入重复");
        }
    }
    public void T000_DELETE_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        IBS.DELETE(SCCGWA, CIRADR, CITADR_RD);
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
