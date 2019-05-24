package com.hisun.BA;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZURCPT {
    DBParm BATLOGP_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FAILURE = "00";
    String K_SUCCESS = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_PROC_RSLT = " ";
    char WF_LOGP_REC_FLAG = 'N';
    BARLOGP BARLOGP = new BARLOGP();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BACURCPT BACURCPT;
    public void MP(SCCGWA SCCGWA, BACURCPT BACURCPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACURCPT = BACURCPT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZURCPT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BACURCPT.RC.RC_APP = "BA";
        BACURCPT.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_IF_REC_EXIST();
        if (pgmRtn) return;
        if (WF_LOGP_REC_FLAG == 'Y') {
            B020_UPDATE_BATLOGP_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_REC_NT_EXST_IN_LOGP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_IF_REC_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARLOGP);
        CEP.TRC(SCCGWA, BACURCPT.COMM_DATA.ORG_SND_TAG);
        CEP.TRC(SCCGWA, BACURCPT.COMM_DATA.ORG_SND_DT);
        CEP.TRC(SCCGWA, BACURCPT.COMM_DATA.ORG_SND_FLW);
        BARLOGP.SND_TAG = BACURCPT.COMM_DATA.ORG_SND_TAG;
        BARLOGP.SND_DT = BACURCPT.COMM_DATA.ORG_SND_DT;
        BARLOGP.SND_FLW = BACURCPT.COMM_DATA.ORG_SND_FLW;
        CEP.TRC(SCCGWA, BARLOGP.SND_TAG);
        CEP.TRC(SCCGWA, BARLOGP.SND_DT);
        CEP.TRC(SCCGWA, BARLOGP.SND_FLW);
        T000_READ_UPDT_BATLOGP_PROC();
        if (pgmRtn) return;
    }
    public void B020_UPDATE_BATLOGP_PROC() throws IOException,SQLException,Exception {
        BARLOGP.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (BACURCPT.COMM_DATA.PROC_RSLT.equalsIgnoreCase(K_FAILURE)) {
            BARLOGP.SND_STS = "04";
        } else {
            BARLOGP.SND_STS = "03";
        }
        BARLOGP.REMK = BACURCPT.COMM_DATA.RTN_MSG;
        T000_UPDATE_BATLOGP_PROC();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_UPDT_BATLOGP_PROC() throws IOException,SQLException,Exception {
        BATLOGP_RD = new DBParm();
        BATLOGP_RD.TableName = "BATLOGP";
        BATLOGP_RD.where = "SND_TAG = :BARLOGP.SND_TAG "
            + "AND SND_DT = :BARLOGP.SND_DT "
            + "AND SND_FLW = :BARLOGP.SND_FLW";
        BATLOGP_RD.upd = true;
        IBS.READ(SCCGWA, BARLOGP, this, BATLOGP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WF_LOGP_REC_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WF_LOGP_REC_FLAG = 'Y';
        } else {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACURCPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_BATLOGP_PROC() throws IOException,SQLException,Exception {
        BATLOGP_RD = new DBParm();
        BATLOGP_RD.TableName = "BATLOGP";
        BATLOGP_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, BARLOGP, BATLOGP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_REC_NT_EXST_IN_LOGP, BACURCPT.RC);
            } else {
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACURCPT.RC);
            }
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
