package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSMREL {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITRISK_RD;
    brParm CITRELN_BR = new brParm();
    DBParm CITRELN_RD;
    DBParm DUPKEY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_REL = 0;
    char WS_RESIDENT = ' ';
    String WS_MSG_INFO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRRELN CIRRELN = new CIRRELN();
    CIRRISK CIRRISK = new CIRRISK();
    CIRPDM CIRPDM = new CIRPDM();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CICOREL CICOREL = new CICOREL();
    CICCINO CICCINO = new CICCINO();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMREL CICSMREL;
    public void MP(SCCGWA SCCGWA, CICSMREL CICSMREL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMREL = CICSMREL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMREL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMREL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSMREL.FUNC);
        if (CICSMREL.FUNC == 'B') {
            B020_BRW_REL_INF();
            if (pgmRtn) return;
        } else if (CICSMREL.FUNC == 'A') {
            B030_ADD_REL_INF();
            if (pgmRtn) return;
        } else if (CICSMREL.FUNC == 'M') {
            B040_MOD_REL_INF();
            if (pgmRtn) return;
        } else if (CICSMREL.FUNC == 'D') {
            B050_DEL_REL_INF();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMREL.DATA.CI_NO);
        if (CICSMREL.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSMREL.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP == '3' 
            && (CICSMREL.FUNC == 'A' 
            || CICSMREL.FUNC == 'M' 
            || CICSMREL.FUNC == 'D')) {
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
            CIRPDM.KEY.CI_NO = CICSMREL.DATA.CI_NO;
            T000_READ_CITPDM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_RESIDENT = CIRPDM.RESIDENT;
            }
        }
        if (CIRBAS.CI_TYP == '2') {
            IBS.init(SCCGWA, CIRCDM);
            CIRCDM.KEY.CI_NO = CICSMREL.DATA.CI_NO;
            T000_READ_CITCDM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_RESIDENT = CIRCDM.RESIDENT;
            }
        }
        if (CIRBAS.CI_TYP == '3') {
            IBS.init(SCCGWA, CIRFDM);
            CIRFDM.KEY.CI_NO = CICSMREL.DATA.CI_NO;
            T000_READ_CITFDM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_RESIDENT = CIRFDM.RESIDENT;
            }
        }
    }
    public void B020_BRW_REL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.KEY.CI_NO = CICSMREL.DATA.CI_NO;
        T000_STARTBR_CITRELN();
        if (pgmRtn) return;
        T000_READNEXT_CITRELN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "RELN INF NOT FND");
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CICOREL);
            CICOREL.DATA.REL_RECD = CIRRELN.KEY.CIREL_CD;
            CICOREL.DATA.REL_NAME = CIRRELN.KEY.RCI_NAME;
            CICOREL.DATA.REL_IDTP = CIRRELN.KEY.RCI_IDTYP;
            CICOREL.DATA.REL_IDNO = CIRRELN.KEY.RCI_IDNO;
            CICOREL.DATA.REL_IDEX = CIRRELN.RCI_ID_EXP;
            CICOREL.DATA.REL_CNTY = CIRRELN.RCI_REG_CNTY;
            CICOREL.DATA.REL_MOB = CIRRELN.RCI_MOB_NO;
            CICOREL.DATA.REL_TEL = CIRRELN.RCI_TEL_NO;
            CICOREL.DATA.REL_SEX = CIRRELN.RCI_SEX;
            CICOREL.DATA.REL_ADCN = CIRRELN.RCI_CNTY_CD;
            CICOREL.DATA.REL_ADDR = CIRRELN.RCI_ADDR;
            CICOREL.DATA.REL_OCNM = CIRRELN.RCI_CORP_NM;
            CICOREL.DATA.REL_BIRT = CIRRELN.RCI_BIRTH;
            CICOREL.DATA.REL_OCCU = CIRRELN.RCI_OCCP;
            CICOREL.DATA.REL_HOLD = CIRRELN.HOLD_RAT;
            CICOREL.DATA.REL_SHDT = CIRRELN.SHARE_DT;
            CICOREL.DATA.REL_NM1 = CIRRELN.RCI_NM1;
            CICOREL.DATA.REL_NM2 = CIRRELN.RCI_NM2;
            CICOREL.DATA.REL_RSDT = CIRRELN.RCI_RESIDENT;
            CICOREL.DATA.REL_BRCT = CIRRELN.RCI_BIR_CNTY;
            CICOREL.DATA.REL_FMCT = CIRRELN.RCI_FARM_CITY;
            CICOREL.DATA.REL_FMAD = CIRRELN.RCI_FARM_ADR;
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITRELN();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRELN();
        if (pgmRtn) return;
    }
    public void B030_ADD_REL_INF() throws IOException,SQLException,Exception {
        if (CICSMREL.DATA.REL_RECD.equalsIgnoreCase("20101")) {
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CICSMREL.DATA.CI_NO;
            CIRRELN.KEY.CIREL_CD = CICSMREL.DATA.REL_RECD;
            T000_READ_CITRELN_RECD();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "法人代表录入重复");
            }
        }
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.KEY.CI_NO = CICSMREL.DATA.CI_NO;
        CIRRELN.KEY.CIREL_CD = CICSMREL.DATA.REL_RECD;
        CIRRELN.KEY.RCI_NAME = CICSMREL.DATA.REL_NAME;
        CIRRELN.KEY.RCI_IDTYP = CICSMREL.DATA.REL_IDTP;
        CIRRELN.KEY.RCI_IDNO = CICSMREL.DATA.REL_IDNO;
        T000_READ_CITRELN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_REL_INF_EXIST, CICSMREL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B042_MOD_CHECK_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSMREL.DATA.REL_IDTP;
        CIRBAS.ID_NO = CICSMREL.DATA.REL_IDNO;
        CIRBAS.CI_NM = CICSMREL.DATA.REL_NAME;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, CICCINO);
            S000_CALL_CIZCINO();
            if (pgmRtn) return;
            CIRRELN.RCI_NO = CICCINO.DATA.CI_NO;
            CEP.TRC(SCCGWA, CICCINO.DATA.CI_NO);
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICCINO.DATA.CI_NO;
            if (CICSMREL.DATA.REL_IDTP == null) CICSMREL.DATA.REL_IDTP = "";
            JIBS_tmp_int = CICSMREL.DATA.REL_IDTP.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSMREL.DATA.REL_IDTP += " ";
            if (CICSMREL.DATA.REL_IDTP.substring(0, 1).equalsIgnoreCase("1")) {
                CIRBAS.CI_TYP = '1';
            } else {
                CIRBAS.CI_TYP = '2';
            }
            CIRBAS.CI_ATTR = '2';
            CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
            CIRBAS.CI_NM = CICSMREL.DATA.REL_NAME;
            BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
            CIRBAS.ID_TYPE = CICSMREL.DATA.REL_IDTP;
            CIRBAS.ID_NO = CICSMREL.DATA.REL_IDNO;
            CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITBAS();
            if (pgmRtn) return;
        } else {
            CIRRELN.RCI_NO = CIRBAS.KEY.CI_NO;
        }
        CIRRELN.RCI_ID_EXP = CICSMREL.DATA.REL_IDEX;
        CIRRELN.RCI_REG_CNTY = CICSMREL.DATA.REL_CNTY;
        CIRRELN.RCI_MOB_NO = CICSMREL.DATA.REL_MOB;
        CIRRELN.RCI_TEL_NO = CICSMREL.DATA.REL_TEL;
        CIRRELN.RCI_SEX = CICSMREL.DATA.REL_SEX;
        CIRRELN.RCI_CNTY_CD = CICSMREL.DATA.REL_ADCN;
        CIRRELN.RCI_ADDR = CICSMREL.DATA.REL_ADDR;
        CEP.TRC(SCCGWA, CICSMREL.DATA.REL_ADDR);
        CEP.TRC(SCCGWA, CIRRELN.RCI_ADDR);
        CIRRELN.RCI_CORP_NM = CICSMREL.DATA.REL_OCNM;
        CIRRELN.RCI_BIRTH = CICSMREL.DATA.REL_BIRT;
        CIRRELN.RCI_OCCP = CICSMREL.DATA.REL_OCCU;
        CIRRELN.HOLD_RAT = CICSMREL.DATA.REL_HOLD;
        CIRRELN.SHARE_DT = CICSMREL.DATA.REL_SHDT;
        CIRRELN.RCI_NM1 = CICSMREL.DATA.REL_NM1;
        CIRRELN.RCI_NM2 = CICSMREL.DATA.REL_NM2;
        CIRRELN.RCI_RESIDENT = CICSMREL.DATA.REL_RSDT;
        CIRRELN.RCI_BIR_CNTY = CICSMREL.DATA.REL_BRCT;
        CIRRELN.RCI_FARM_CITY = CICSMREL.DATA.REL_FMCT;
        CIRRELN.RCI_FARM_ADR = CICSMREL.DATA.REL_FMAD;
        CIRRELN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRRELN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRRELN.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRRELN.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRRELN.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRRELN.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITRELN();
        if (pgmRtn) return;
    }
    public void B040_MOD_REL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.KEY.CI_NO = CICSMREL.DATA.CI_NO;
        CIRRELN.KEY.CIREL_CD = CICSMREL.DATA.REL_RECD;
        CIRRELN.KEY.RCI_NAME = CICSMREL.DATA.REL_NAME;
        CIRRELN.KEY.RCI_IDTYP = CICSMREL.DATA.REL_IDTP;
        CIRRELN.KEY.RCI_IDNO = CICSMREL.DATA.REL_IDNO;
        T000_READ_CITRELN_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RELN_NOTFND, CICSMREL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B041_FATCA_CHK_INFO();
        if (pgmRtn) return;
        B042_MOD_CHECK_INFO();
        if (pgmRtn) return;
        CIRRELN.RCI_ID_EXP = CICSMREL.DATA.REL_IDEX;
        CIRRELN.RCI_REG_CNTY = CICSMREL.DATA.REL_CNTY;
        CIRRELN.RCI_MOB_NO = CICSMREL.DATA.REL_MOB;
        CIRRELN.RCI_TEL_NO = CICSMREL.DATA.REL_TEL;
        CIRRELN.RCI_SEX = CICSMREL.DATA.REL_SEX;
        CIRRELN.RCI_CNTY_CD = CICSMREL.DATA.REL_ADCN;
        CIRRELN.RCI_ADDR = CICSMREL.DATA.REL_ADDR;
        CEP.TRC(SCCGWA, CICSMREL.DATA.REL_ADDR);
        CEP.TRC(SCCGWA, CIRRELN.RCI_ADDR);
        CIRRELN.RCI_CORP_NM = CICSMREL.DATA.REL_OCNM;
        CIRRELN.RCI_BIRTH = CICSMREL.DATA.REL_BIRT;
        CIRRELN.RCI_OCCP = CICSMREL.DATA.REL_OCCU;
        CIRRELN.HOLD_RAT = CICSMREL.DATA.REL_HOLD;
        CIRRELN.SHARE_DT = CICSMREL.DATA.REL_SHDT;
        CIRRELN.RCI_NM1 = CICSMREL.DATA.REL_NM1;
        CIRRELN.RCI_NM2 = CICSMREL.DATA.REL_NM2;
        CIRRELN.RCI_RESIDENT = CICSMREL.DATA.REL_RSDT;
        CIRRELN.RCI_BIR_CNTY = CICSMREL.DATA.REL_BRCT;
        CIRRELN.RCI_FARM_CITY = CICSMREL.DATA.REL_FMCT;
        CIRRELN.RCI_FARM_ADR = CICSMREL.DATA.REL_FMAD;
        CIRRELN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRRELN.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRRELN.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITRELN();
        if (pgmRtn) return;
    }
    public void B041_FATCA_CHK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = CICSMREL.DATA.CI_NO;
        T000_READ_CITRISK();
        if (pgmRtn) return;
        if (CIRBAS.CI_TYP == '2' 
            || CIRBAS.CI_TYP == '3') {
            if ((CIRRELN.KEY.CIREL_CD.equalsIgnoreCase("20202") 
                && !CIRRELN.RCI_REG_CNTY.equalsIgnoreCase("USA") 
                && CICSMREL.DATA.REL_RECD.equalsIgnoreCase("20202") 
                && CICSMREL.DATA.REL_CNTY.equalsIgnoreCase("USA") 
                && CIRRISK.FATCA_TP.equalsIgnoreCase("W800")) 
                || (CIRRELN.KEY.CIREL_CD.equalsIgnoreCase("20202") 
                && CIRRELN.RCI_REG_CNTY.equalsIgnoreCase("USA") 
                && CICSMREL.DATA.REL_RECD.equalsIgnoreCase("20202") 
                && !CICSMREL.DATA.REL_CNTY.equalsIgnoreCase("USA") 
                && CIRRISK.FATCA_TP.equalsIgnoreCase("W900"))) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MOD_FATCA_TP);
            }
        }
    }
    public void B042_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RESIDENT);
        if (WS_RESIDENT != '1') {
            if (CICSMREL.DATA.REL_RECD.equalsIgnoreCase("20202")) {
                if (CICSMREL.DATA.REL_NM1.trim().length() == 0 
                    || CICSMREL.DATA.REL_NM2.trim().length() == 0 
                    || CICSMREL.DATA.REL_RSDT == ' ' 
                    || CICSMREL.DATA.REL_BRCT.trim().length() == 0 
                    || CICSMREL.DATA.REL_FMCT.trim().length() == 0 
                    || CICSMREL.DATA.REL_FMAD.trim().length() == 0) {
                    WS_MSG_INFO = " ";
                    WS_MSG_INFO = "控制人姓、名�?" + "居民性质�?" + "英文地址 必输";
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, WS_MSG_INFO);
                }
            }
        }
    }
    public void B050_DEL_REL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.KEY.CI_NO = CICSMREL.DATA.CI_NO;
        CIRRELN.KEY.CIREL_CD = CICSMREL.DATA.REL_RECD;
        CIRRELN.KEY.RCI_NAME = CICSMREL.DATA.REL_NAME;
        CIRRELN.KEY.RCI_IDTYP = CICSMREL.DATA.REL_IDTP;
        CIRRELN.KEY.RCI_IDNO = CICSMREL.DATA.REL_IDNO;
        T000_READ_CITRELN_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RELN_NOTFND, CICSMREL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_DELETE_CITRELN();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_REL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOREL);
        SCCMPAG.DATA_LEN = 979;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-NO", CICCINO);
        if (CICCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCINO.RC);
        }
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
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_READ_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.READ(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_WRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.WRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_STARTBR_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_BR.rp = new DBParm();
        CITRELN_BR.rp.TableName = "CITRELN";
        CITRELN_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRRELN, CITRELN_BR);
    }
    public void T000_READNEXT_CITRELN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRRELN, this, CITRELN_BR);
    }
    public void T000_ENDBR_CITRELN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITRELN_BR);
    }
    public void T000_READ_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        IBS.READ(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void T000_READ_CITRELN_RECD() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        CITRELN_RD.eqWhere = "CI_NO,CIREL_CD";
        IBS.READ(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void T000_READ_CITRELN_UPD() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        CITRELN_RD.upd = true;
        IBS.READ(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void T000_WRITE_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        IBS.WRITE(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void T000_REWRITE_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        CITRELN_RD.errhdl = true;
        DUPKEY_RD = new DBParm();
        DUPKEY_RD.TableName = "DUPKEY";
        IBS.REWRITE(SCCGWA, DURKEY, DUPKEY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "关系人信息录入重�?");
        }
    }
    public void T000_DELETE_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        IBS.DELETE(SCCGWA, CIRRELN, CITRELN_RD);
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
